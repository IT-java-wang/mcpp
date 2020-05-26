package com.yada.qrcode.payment.vspos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yada.qrcode.payment.common.pfx.RsaSha1;
import com.yada.qrcode.payment.common.sequence.sequence.SequenceGeneratorDbSequence;
import com.yada.qrcode.payment.common.util.DateTimeFormatUtil;
import com.yada.qrcode.payment.vspos.entity.ServiceUrlInfo;
import com.yada.qrcode.payment.vspos.entity.enums.SendResultEnum;
import com.yada.qrcode.payment.vspos.entity.proc.TerminalPositionInfo;
import com.yada.qrcode.payment.vspos.entity.send.SendResult;
import com.yada.qrcode.payment.vspos.entity.send.TranResult;
import com.yada.qrcode.payment.vspos.entity.send.base.ReqMessage;
import com.yada.qrcode.payment.vspos.entity.send.manager.req.InitReqMsgBody;
import com.yada.qrcode.payment.vspos.entity.send.manager.req.ManagerMessageGeneralHeader;
import com.yada.qrcode.payment.vspos.entity.send.manager.req.PositionUploadMsgBody;
import com.yada.qrcode.payment.vspos.entity.send.manager.req.SingInReqReqMsgBody;
import com.yada.qrcode.payment.vspos.entity.send.manager.resp.DownloadInitParamRespBody;
import com.yada.qrcode.payment.vspos.entity.send.manager.resp.DownloadTmkRespMsgBody;
import com.yada.qrcode.payment.vspos.entity.send.manager.resp.PosRespMessage;
import com.yada.qrcode.payment.vspos.entity.send.manager.resp.SingInRespMsgBody;
import com.yada.qrcode.payment.vspos.entity.send.manager.result.DownloadInitParameterResult;
import com.yada.qrcode.payment.vspos.entity.send.manager.result.DownloadTmkResult;
import com.yada.qrcode.payment.vspos.entity.send.manager.result.PositionUploadResult;
import com.yada.qrcode.payment.vspos.entity.send.manager.result.SingInResult;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.RespMessage;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.req.*;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp.*;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.header.TranMsgHeader;
import com.yada.qrcode.payment.vspos.spos.CheckTranType;
import com.yada.qrcode.payment.vspos.spos.PayTypeEnum;
import com.yada.qrcode.payment.vspos.spos.SendTran;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;

/**
 * 智能POS交易
 *
 * @author quhan
 */
public class SmartPosTraner {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmartPosTraner.class);

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 终端号
     */
    private String terminalId;

    /**
     * 需要使用的URL的集合
     */
    private ServiceUrlInfo urls;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 加密工具
     */
    private TerminalAuth terminalAuth;

    /**
     * 流水号生成器
     */
    private SequenceGeneratorDbSequence traceNoSeqGenerator;

    /**
     * 业务编号
     */
    private String bussId;

    /**
     * 前端系统标识
     */
    private String sysNo;

    /**
     * 检查签到
     */
    private BiConsumer<RespMessage<?>, String> checkSingIn;

    /**
     * 报文版本,初始版本号为1000
     */
    private String msgVer = "1000";

    /**
     * 接入商户类型，传01
     */
    private String merType = "01";

    /**
     * 构造方法
     *
     * @param merchantId          商户号
     * @param terminalId          终端号
     * @param urlInfo             URL集合
     * @param timeout             超时时间
     * @param batchNo             批次号
     * @param terminalAuth        加密工具
     * @param traceNoSeqGenerator 流水号生成器
     * @param bussId              业务编号
     * @param sysNo               前端系统标识
     * @param checkSingIn         检查签到方法
     */
    public SmartPosTraner(String merchantId, String terminalId, ServiceUrlInfo urlInfo, int timeout, String batchNo,
                          TerminalAuth terminalAuth, SequenceGeneratorDbSequence traceNoSeqGenerator, String bussId, String sysNo,
                          BiConsumer<RespMessage<?>, String> checkSingIn) {
        this.merchantId = merchantId;
        this.terminalId = terminalId;
        this.urls = urlInfo;
        this.timeout = timeout;
        this.batchNo = batchNo;
        this.terminalAuth = terminalAuth;
        this.traceNoSeqGenerator = traceNoSeqGenerator;
        this.bussId = bussId;
        this.sysNo = sysNo;
        this.checkSingIn = checkSingIn;
    }

    /**
     * 签到交易
     *
     * @return 签到信息
     */
    public SingInResult singIn() {

        SingInResult singInResult = new SingInResult();

        ManagerMessageGeneralHeader msgHeader = new ManagerMessageGeneralHeader();
        msgHeader.setSposVersion("2.0");
        msgHeader.setTranId("501002");
        msgHeader.setInDate(DateTimeFormatUtil.getYYYYMMDDDate());
        msgHeader.setInTime(DateTimeFormatUtil.getHHMMSSTime());

        ReqMessage<SingInReqReqMsgBody> posReqMessage = new ReqMessage<>(msgHeader, new SingInReqReqMsgBody());

        PosRespMessage<SingInRespMsgBody> posRespMessage = null;
        try {
            String response = SendTran.sendTran(urls.getSingInTranServerUrl(), posReqMessage, timeout);
            posRespMessage = JSON.parseObject(response, new TypeReference<PosRespMessage<SingInRespMsgBody>>() {
            });
        } catch (Exception e) {
            LOGGER.error("签到交易发生异常，", e);
            singInResult.setSingInRetCode("9999");
            singInResult.setSingInRetMsg("加密机连接错误");
            return singInResult;
        }

        if (posRespMessage != null) {
            LOGGER.info("签到接口调用成功，返回状态码：" + posRespMessage.getRetCode());
            singInResult.setSingInRetCode(posRespMessage.getRetCode());
            singInResult.setSingInRetMsg(posRespMessage.getRetMsg());

            SingInRespMsgBody respBody = posRespMessage.getBody();

            if (respBody == null) {
                return singInResult;
            }

            singInResult.setMerNo(respBody.getMerchantId());
            singInResult.setTermNo(respBody.getTerminalId());
            singInResult.setZekKeyInd(respBody.getZekKeyInd());
            singInResult.setDekZek(respBody.getZekDek());

        }

        return singInResult;
    }

    /**
     * 下载收单主密钥交易（参数下载交易）
     *
     * @param firmCode  厂商代码
     * @param tuSn      SN号
     * @param zekKeyInd 密钥索引
     * @return 下载收单主密钥结果
     */
    public DownloadTmkResult paramDownload(String firmCode, String tuSn, String zekKeyInd) {

        DownloadTmkResult result = new DownloadTmkResult();

        String inDate = DateTimeFormatUtil.getYYYYMMDDDate();
        String inTime = DateTimeFormatUtil.getHHMMSSTime();

        TreeMap<String, String> params = new TreeMap<>(String::compareTo);
        params.put("FirmCode", firmCode);
        params.put("SposVersion", "2.0");
        params.put("Tusn", tuSn);
        params.put("InDate", inDate);
        params.put("InTime", inTime);
        params.put("TranId", "501003");
        params.put("ZekKeyInd", zekKeyInd);

        String sign;
        try {
            String paramsString = mapToString(params);
            sign = terminalAuth.getSign(paramsString);
        } catch (Exception e) {
            LOGGER.error("收单主密钥下载签名失败");
            result.setRetCode("9999");
            result.setRetMsg("加密机连接错误");
            return result;
        }

        ManagerMessageGeneralHeader msgHeader = new ManagerMessageGeneralHeader();

        msgHeader.setInDate(inDate);
        msgHeader.setInTime(inTime);
        msgHeader.setSposVersion("2.0");
        msgHeader.setTranId("501003");
        msgHeader.setSign(sign);
        msgHeader.setZekKeyInd(zekKeyInd);

        ReqMessage<?> posReqMessage = new ReqMessage<>(msgHeader, null);
        PosRespMessage<DownloadTmkRespMsgBody> posRespMessage;
        try {
            String response = SendTran.sendTran(urls.getDownloadTmkServerUrl(), posReqMessage, timeout);
            posRespMessage = JSON.parseObject(response, new TypeReference<PosRespMessage<DownloadTmkRespMsgBody>>() {
            });
        } catch (Exception e) {
            LOGGER.error("下载收单主秘钥交易发生异常，", e);
            result.setRetCode("9999");
            result.setRetMsg("下载收单主秘钥时网络错误");
            return result;
        }

        if (posRespMessage != null) {
            result.setRetCode(posRespMessage.getRetCode());
            result.setRetMsg(posRespMessage.getRetMsg());

            if (posRespMessage.getBody() != null) {
                result.setDekTmk(posRespMessage.getBody().getTmkDek());
            }
        }

        return result;
    }

    /**
     * 下载设备初始化参数（下载Dek信息）
     *
     * @param superPosSn 母PosSn号码
     * @param sn         子PosSn号码
     * @param firmCode   子Pos厂商代码
     * @param privateKey 母Pos私钥
     * @return 设备初始化参数信息
     */
    public DownloadInitParameterResult downloadInitializations(String superPosSn, String sn, String firmCode, PrivateKey privateKey) {
        DownloadInitParameterResult downloadInitParameterResult = new DownloadInitParameterResult();

        ManagerMessageGeneralHeader msgHeader = new ManagerMessageGeneralHeader();
        msgHeader.setSposVersion("2.0");
        msgHeader.setTranId("501001");
        String inDate = DateTimeFormatUtil.getYYYYMMDDDate();
        msgHeader.setInDate(inDate);
        String inTime = DateTimeFormatUtil.getHHMMSSTime();
        msgHeader.setInTime(inTime);
        msgHeader.setZekKeyInd("");

        InitReqMsgBody initReqMsgBody = new InitReqMsgBody();
        initReqMsgBody.setKeyDeviceSn(superPosSn);
        String timestamp = DateTimeFormatUtil.getYYYYMMDDHHMMSSDate();
        initReqMsgBody.setTimestamp(timestamp);

        TreeMap<String, String> params = new TreeMap<>(String::compareTo);
        params.put("FirmCode", firmCode);
        params.put("KeyDeviceSn", superPosSn);
        params.put("SposVersion", "2.0");
        params.put("Timestamp", timestamp);
        params.put("TranId", "501001");
        params.put("Tusn", sn);
        params.put("InDate", inDate);
        params.put("InTime", inTime);
        params.put("ZekKeyInd", "");

        String sign = null;
        try {
            sign = Hex.encodeHexString(RsaSha1.sign(mapToString(params), privateKey, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        msgHeader.setSign(sign);

        ReqMessage<InitReqMsgBody> posInitReqMessage = new ReqMessage<>(msgHeader, initReqMsgBody);
        PosRespMessage<DownloadInitParamRespBody> posRespMessage;
        try {
            String response = SendTran.sendTran(urls.getDownloadInitParameterServerUrl(), posInitReqMessage, timeout);
            posRespMessage = JSON.parseObject(response, new TypeReference<PosRespMessage<DownloadInitParamRespBody>>() {
            });
        } catch (IOException e) {
            LOGGER.error("下载设备初始化参数发生错误：", e);
            downloadInitParameterResult.setRetCode("9999");
            downloadInitParameterResult.setRetMsg("下载设备初始化参数时网络错误");
            return downloadInitParameterResult;
        }

        if (posRespMessage != null) {
            LOGGER.info("下载设备主秘钥接口响应数据：" + posRespMessage);
            downloadInitParameterResult.setRetCode(posRespMessage.getRetCode());
            downloadInitParameterResult.setRetMsg(posRespMessage.getRetMsg());

            DownloadInitParamRespBody downloadInitParamRespBody = posRespMessage.getBody();
            if (downloadInitParamRespBody != null) {
                downloadInitParameterResult.setDekZmk(downloadInitParamRespBody.getDekZmk());
                downloadInitParameterResult.setDekKcv(downloadInitParamRespBody.getDekKcv());
            }
        } else {
            LOGGER.error("下载设备主秘钥接口响应数据为空");
        }

        return downloadInitParameterResult;
    }


    /**
     * 初始化位置参数上传接口
     *
     * @param info      位置参数
     * @param zekKeyInd 秘钥索引
     * @return 初始化位置上传结果
     */
    public PositionUploadResult positionUpload(TerminalPositionInfo info, String zekKeyInd) {

        PositionUploadResult result = new PositionUploadResult();

        String inDate = DateTimeFormatUtil.getYYYYMMDDDate();
        String inTime = DateTimeFormatUtil.getHHMMSSTime();

        TreeMap<String, String> params = new TreeMap<>(String::compareTo);
        params.put("FirmCode", urls.getVendorCode());
        params.put("SposVersion", "2.0");
        params.put("Tusn", urls.getTuSn());
        params.put("InDate", inDate);
        params.put("InTime", inTime);
        params.put("TranId", "501012");
        params.put("ZekKeyInd", zekKeyInd);
        params.put("Position", info.getPosition());
        params.put("Province", info.getProvince());
        params.put("City", info.getCity());
        params.put("District", info.getDistrict());
        params.put("Town", info.getTown());
        params.put("Village", info.getVillage());
        params.put("Street", info.getStreet());
        params.put("StreetNo", info.getStreetNo());


        String sign;
        try {
            String paramsString = mapToString(params);
            sign = terminalAuth.getSign(paramsString);
        } catch (Exception e) {
            LOGGER.error("收单主密钥下载签名失败");
            result.setRetCode("9999");
            result.setRetMsg("加密机连接错误");
            return result;
        }

        ManagerMessageGeneralHeader msgHeader = new ManagerMessageGeneralHeader();

        msgHeader.setInDate(inDate);
        msgHeader.setInTime(inTime);
        msgHeader.setSposVersion("2.0");
        msgHeader.setTranId("501003");
        msgHeader.setSign(sign);
        msgHeader.setZekKeyInd(zekKeyInd);

        PositionUploadMsgBody positionUploadMsgBody = new PositionUploadMsgBody(
                info.getPosition(), info.getProvince(), info.getCity(), info.getDistrict(),
                info.getTown(), info.getVillage(), info.getStreet(), info.getStreetNo());


        ReqMessage<?> posReqMessage = new ReqMessage<>(msgHeader, positionUploadMsgBody);
        PosRespMessage<?> posRespMessage;
        try {
            String response = SendTran.sendTran(urls.getDownloadTmkServerUrl(), posReqMessage, timeout);
            posRespMessage = JSON.parseObject(response, new TypeReference<PosRespMessage<?>>() {
            });
        } catch (Exception e) {
            LOGGER.error("下载收单主秘钥交易发生异常，", e);
            result.setRetCode("9999");
            result.setRetMsg("下载收单主秘钥时网络错误");
            return result;
        }

        if (posRespMessage != null) {
            result.setRetCode(posRespMessage.getRetCode());
            result.setRetMsg(posRespMessage.getRetMsg());

            return result;
        } else {
            LOGGER.error("初始化位置上送接口返回数据为空");
            result.setRetCode("9999");
            result.setRetMsg("初始化位置上送接口返回数据为空");
            return result;
        }

    }


    /**
     * Map转换为String方法，拼接格式为 key1=value1&key2=value2
     *
     * @param map 需要转换的Map
     * @return 转换获取的String字符串
     */
    private static String mapToString(Map<String, String> map) {
        Set<String> keySet = map.keySet();

        StringBuilder sb = new StringBuilder();
        for (String key : keySet) {
            if ("".contentEquals(sb)) {
                sb.append(key).append("=").append(map.get(key));
            } else {
                sb.append("&").append(key).append("=").append(map.get(key));
            }
        }
        return sb.toString();
    }


    /**
     * 消费交易
     *
     * @param authCode     交易授权码（扫码）
     * @param amt          金额
     * @param merOrderNo   商户订单号
     * @param tranDate     交易日期
     * @param tranTime     交易时间
     * @param traceNo      流水号
     * @param localOrderNo 本地订单号（PayLs）
     * @param ccyCode      币种
     * @param payValidTime 支付有效时间，最短1分钟，最长10分钟
     * @return 消费交易请求以及消费交易响应
     */
    public TranResult<PaymentReqMsgBody, PaymentRespMsgBody> payment(String authCode, String amt, String merOrderNo, String tranDate,
                                                                     String tranTime, String traceNo, String localOrderNo, String ccyCode,
                                                                     String payValidTime) {

        PayTypeEnum payTypeEnum = CheckTranType.checkPayType(authCode);

        TranResult<PaymentReqMsgBody, PaymentRespMsgBody> tranResult = new TranResult<>();

        if (payTypeEnum == null) {
            LOGGER.error("交易授权码无效：" + authCode);
            tranResult.setNetCode("2002");
            tranResult.setNetMsg("交易授权码无效");
            return tranResult;
        }

        //消息体
        PaymentReqMsgBody msgBody = new PaymentReqMsgBody(this.merchantId, this.terminalId, localOrderNo, traceNo,
                this.batchNo, payTypeEnum.getValue(), authCode, amt, ccyCode, merOrderNo, null, payValidTime);

        //消息头
        TranMsgHeader header = new TranMsgHeader(this.msgVer, tranDate, tranTime, payTypeEnum.getTranId(), bussId, this.merType,
                null, null, sysNo, null);

        ReqMessage<PaymentReqMsgBody> msgData = new ReqMessage<>(header, msgBody);
        tranResult.setReqMessage(msgData);

        SendResult<PaymentRespMsgBody> paymentRespMsgBodySendResult = SendTran.sendTran(urls.getWaTranServerUrl(), this.terminalAuth, msgData, timeout, PaymentRespMsgBody.class);

        this.checkSingIn.accept(paymentRespMsgBodySendResult.getRespMessage(), this.batchNo);

        if (paymentRespMsgBodySendResult.getSendResultEnum() == SendResultEnum.OK) {
            //交易发送成功
            tranResult.setNetCode("1000");
            tranResult.setNetMsg("成功");
            tranResult.setRespMessage(paymentRespMsgBodySendResult.getRespMessage());
        } else if (paymentRespMsgBodySendResult.getSendResultEnum() == SendResultEnum.NET_ERROR) {
            //交易发送成功
            tranResult.setNetCode("2001");
            tranResult.setNetMsg("连接总行服务器失败");
            tranResult.setRespMessage(paymentRespMsgBodySendResult.getRespMessage());
        } else if (paymentRespMsgBodySendResult.getSendResultEnum() == SendResultEnum.MACHINE_ERROR) {
            LOGGER.error("加密机处理失败");
            tranResult.setNetCode("2003");
            tranResult.setNetMsg(paymentRespMsgBodySendResult.getErrorMsg());
        } else {
            LOGGER.error("消费交易发送失败");
            tranResult.setNetCode("2000");
            tranResult.setNetMsg(paymentRespMsgBodySendResult.getErrorMsg());
        }

        return tranResult;
    }


    /**
     * 退货交易
     *
     * @param merOrderNo     商户订单号
     * @param oldPayType     原支付方式
     * @param oldBankDate    原交易银行日期
     * @param oldBankOrderNo 原交易银行订单号（支付宝微信显示商户订单号）
     * @param oldPayVounum   原交易银联授权码（仅原交易是银联交易时才有）
     * @param refundAmt      退款金额
     * @return 退货交易请求以及退货交易响应
     */
    public TranResult<RefundReqMsgBody, RefundRespMsgBody> refund(String merOrderNo, String oldPayType, String oldBankDate,
                                                                  String oldBankOrderNo, String oldPayVounum, String refundAmt,
                                                                  String tranDate, String tranTime,
                                                                  String traceNo, String localOrderNo) {


        TranResult<RefundReqMsgBody, RefundRespMsgBody> tranResult = new TranResult<>();

        RefundReqMsgBody refundReqMsgBody = new RefundReqMsgBody(this.merchantId, this.terminalId, localOrderNo, traceNo,
                this.batchNo, merOrderNo, oldPayType, oldBankDate, oldBankOrderNo, oldPayVounum, refundAmt);

        TranMsgHeader header = new TranMsgHeader(this.msgVer, tranDate, tranTime, "201005", bussId, this.merType,
                null, null, sysNo, null);

        ReqMessage<RefundReqMsgBody> msgData = new ReqMessage<>(header, refundReqMsgBody);

        SendResult<RefundRespMsgBody> sendResult = SendTran.sendTran(urls.getWaTranServerUrl(), this.terminalAuth, msgData, timeout, RefundRespMsgBody.class);

        this.checkSingIn.accept(sendResult.getRespMessage(), this.batchNo);

        tranResult.setReqMessage(msgData);
        if (sendResult.getSendResultEnum() == SendResultEnum.OK) {
            //交易发送成功
            tranResult.setNetCode("1000");
            tranResult.setNetMsg("成功");
            tranResult.setRespMessage(sendResult.getRespMessage());
        } else {
            LOGGER.error("退货交易发送失败");
            tranResult.setNetCode("2000");
            tranResult.setNetMsg(sendResult.getErrorMsg());
        }

        return tranResult;
    }

    /**
     * 撤销交易
     *
     * @param merOrderNo     商户订单号
     * @param oldPayType     原交易类型（付款方式）
     * @param oldBankOrderNo 原交易银行订单号（支付宝微信显示商户订单号,仅微信支付宝交易才有）
     * @param oldPayNo       原交易银联支付订单号（仅原交易是银联交易才有）
     * @param oldTranId      原交易交易编码
     * @return 撤销交易请求以及撤销交易响应
     */
    public TranResult<RevokeReqMsgBody, RevokeRespMsgBody> revoke(String merOrderNo, String oldPayType, String oldBankOrderNo,
                                                                  String oldPayNo, String oldTranId, String tranDate,
                                                                  String tranTime, String traceNo, String localOrderNo) {

        TranResult<RevokeReqMsgBody, RevokeRespMsgBody> tranResult = new TranResult<>();

        RevokeReqMsgBody revokeReq = new RevokeReqMsgBody(this.merchantId, this.terminalId, localOrderNo, traceNo,
                this.batchNo, merOrderNo, oldPayType, oldTranId, oldBankOrderNo, oldPayNo, null
        );

        TranMsgHeader header = new TranMsgHeader(this.msgVer, tranDate, tranTime, "201004", bussId, this.merType,
                null, null, sysNo, null);

        ReqMessage<RevokeReqMsgBody> msgData = new ReqMessage<>(header, revokeReq);

        SendResult<RevokeRespMsgBody> sendResult = SendTran.sendTran(urls.getWaTranServerUrl(), this.terminalAuth, msgData, timeout, RevokeRespMsgBody.class);

        this.checkSingIn.accept(sendResult.getRespMessage(), this.batchNo);

        tranResult.setReqMessage(msgData);
        if (sendResult.getSendResultEnum() == SendResultEnum.OK) {
            //交易发送成功
            tranResult.setNetCode("1000");
            tranResult.setNetMsg("成功");
            tranResult.setRespMessage(sendResult.getRespMessage());
        } else {
            LOGGER.error("撤销交易发送失败");
            tranResult.setNetCode("2000");
            tranResult.setNetMsg(sendResult.getErrorMsg());
        }

        return tranResult;

    }

    /**
     * 消费交易查询
     *
     * @param oldPayType   原交易付款方式
     * @param oldTranId    原交易交易编码
     * @param oldPayLs     原交易前端唯一流水号（LocalOrderNo）
     * @param oldTranAmt   原交易金额
     * @param tranDate     交易日期
     * @param tranTime     交易时间
     * @param traceNo      流水号
     * @param localOrderNo 本地交易订单号（PayLs）
     * @param oldCcyCode   原交易币种
     * @return 查询交易请求以及查询交易响应
     */
    public TranResult<QueryReqMsgBody, QueryRespMsgBody> query(String oldPayType, String oldTranId, String oldPayLs, String oldTranAmt,
                                                               String tranDate, String tranTime, String traceNo, String localOrderNo, String oldCcyCode) {

        TranResult<QueryReqMsgBody, QueryRespMsgBody> tranResult = new TranResult<>();

        QueryReqMsgBody sposQueryReq = new QueryReqMsgBody(this.merchantId, this.terminalId, localOrderNo, traceNo,
                this.batchNo, oldPayType, oldTranId, oldPayLs, oldTranAmt, oldCcyCode, null);

        TranMsgHeader header = new TranMsgHeader(this.msgVer, tranDate, tranTime, "201006", bussId, this.merType,
                null, null, sysNo, null);

        ReqMessage<QueryReqMsgBody> msgData = new ReqMessage<>(header, sposQueryReq);

        LOGGER.info("消费查询方法请求：" + msgData.toString());
        SendResult<QueryRespMsgBody> sendResult = SendTran.sendTran(urls.getWaTranServerUrl(), this.terminalAuth, msgData, timeout, QueryRespMsgBody.class);

        this.checkSingIn.accept(sendResult.getRespMessage(), this.batchNo);

        tranResult.setReqMessage(msgData);
        if (sendResult.getSendResultEnum() == SendResultEnum.OK) {
            //交易发送成功
            tranResult.setNetCode("1000");
            tranResult.setNetMsg("成功");
            tranResult.setRespMessage(sendResult.getRespMessage());
        } else {
            LOGGER.error("消费查询交易发送失败");
            tranResult.setNetCode("2000");
            tranResult.setNetMsg(sendResult.getErrorMsg());
        }

        return tranResult;
    }

    /**
     * 退货、撤销交易查询
     *
     * @param oldPayType   原交易付款方式
     * @param oldTranId    原交易编码
     * @param oldPayLs     原交易前端唯一流水号
     * @param oldRefundAmt 原交易退款金额
     * @param tranDate     交易日期
     * @param tranTime     交易时间
     * @param traceNo      流水号
     * @param localOrderNo 本地订单号（PayLs）
     * @param oldCcyCode   原交易币种
     * @return 退货、撤销结果查询交易请求以及退货、撤销结果查询交易响应
     */
    public TranResult<ReverseQueryReqMsgBody, ReverseQueryRespMsgBody> reverseQuery(String oldPayType, String oldTranId,
                                                                                    String oldPayLs, String oldRefundAmt,
                                                                                    String tranDate, String tranTime,
                                                                                    String traceNo, String localOrderNo, String oldCcyCode) {

        TranResult<ReverseQueryReqMsgBody, ReverseQueryRespMsgBody> tranResult = new TranResult<>();

        ReverseQueryReqMsgBody body = new ReverseQueryReqMsgBody(this.merchantId, this.terminalId, localOrderNo, traceNo,
                this.batchNo, oldPayType, oldTranId, oldPayLs, oldRefundAmt, oldCcyCode, null);

        TranMsgHeader header = new TranMsgHeader(this.msgVer, tranDate, tranTime, "201007", bussId, this.merType,
                null, null, sysNo, null);

        ReqMessage<ReverseQueryReqMsgBody> msgData = new ReqMessage<>(header, body);

        LOGGER.info("退货、撤销查询方法请求：" + msgData.toString());
        SendResult<ReverseQueryRespMsgBody> sendResult = SendTran.sendTran(urls.getWaTranServerUrl(), this.terminalAuth, msgData, timeout, ReverseQueryRespMsgBody.class);

        this.checkSingIn.accept(sendResult.getRespMessage(), this.batchNo);

        tranResult.setReqMessage(msgData);
        if (sendResult.getSendResultEnum() == SendResultEnum.OK) {
            //交易发送成功
            tranResult.setNetCode("1000");
            tranResult.setNetMsg("成功");
            tranResult.setRespMessage(sendResult.getRespMessage());
        } else {
            LOGGER.error("退货/撤消查询交易发送失败");
            tranResult.setNetCode("2000");
            tranResult.setNetMsg(sendResult.getErrorMsg());
        }

        return tranResult;
    }

    public String getTraceNo() {
        return String.format("%06d", traceNoSeqGenerator.getSequence());
    }
}
