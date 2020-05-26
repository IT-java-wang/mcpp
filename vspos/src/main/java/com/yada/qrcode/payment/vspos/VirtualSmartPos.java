package com.yada.qrcode.payment.vspos;

import com.yada.qrcode.payment.common.pfx.PfxCertUtil;
import com.yada.qrcode.payment.common.pfx.RsaSha1;
import com.yada.qrcode.payment.common.sequence.sequence.SequenceGeneratorDbSequence;
import com.yada.qrcode.payment.common.sequence.sequence.SequenceGeneratorDbSequenceUtil;
import com.yada.qrcode.payment.encryption.IEncryption;
import com.yada.qrcode.payment.vspos.entity.ServiceUrlInfo;
import com.yada.qrcode.payment.vspos.entity.proc.SuperPos;
import com.yada.qrcode.payment.vspos.entity.proc.Terminal;
import com.yada.qrcode.payment.vspos.entity.proc.TerminalPositionInfo;
import com.yada.qrcode.payment.vspos.entity.send.manager.result.DownloadInitParameterResult;
import com.yada.qrcode.payment.vspos.entity.send.manager.result.DownloadTmkResult;
import com.yada.qrcode.payment.vspos.entity.send.manager.result.PositionUploadResult;
import com.yada.qrcode.payment.vspos.entity.send.manager.result.SingInResult;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.RespMessage;
import com.yada.qrcode.payment.vspos.exception.DekErrorException;
import com.yada.qrcode.payment.vspos.exception.DownloadTmkException;
import com.yada.qrcode.payment.vspos.exception.SingInException;
import com.yada.qrcode.payment.vspos.exception.SuperPosIsNullException;
import com.yada.qrcode.payment.vspos.spos.SmartPosStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;
import java.util.function.BiConsumer;

/**
 * 虚拟智能POS
 *
 * @author quhan
 */
public class VirtualSmartPos implements IVirtualSmartPos<SmartPosTraner> {

    private final static Logger LOGGER = LoggerFactory.getLogger(VirtualSmartPos.class);

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 终端号
     */
    private String terminalId;

    /**
     * 交易URL
     */
    private ServiceUrlInfo urls;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 加密工具类
     */
    private TerminalAuth terminalAuth;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 流水号生成器
     */
    private SequenceGeneratorDbSequence traceNoSeqGenerator;

    /**
     * SN号码
     */
    private String tuSn;

    /**
     * 厂商代码
     */
    private String vendorCode;

    /**
     * 密钥索引
     */
    private String zekKeyInd;

    /**
     * 智能POS存储
     */
    private SmartPosStorage smartPosStorage;

    /**
     * 业务编号
     */
    private String bussId;

    /**
     * 前置系统标识
     */
    private String sysNo;

    private BiConsumer<RespMessage<?>, String> checkSingInConsumer = (respMessage, messageBatchNo) -> {
        boolean messageNeedSingIn;
        String retCode = respMessage.getRetCode();

        switch (retCode) {
            case "2302":
            case "2303":
            case "2305":
            case "4403":
                messageNeedSingIn = true;
                break;
            default:
                messageNeedSingIn = false;
        }

        if (messageNeedSingIn && this.smartPosStorage.getBatchNo(this.vendorCode, this.tuSn).equals(messageBatchNo)) {
            this.smartPosStorage.saveNeedSignInStatus(this.vendorCode, this.tuSn, true);
        }

    };

    /**
     * 完整构造器，当 lmkDek 和 lmkZek都不为空的时候，设备不会进行签到,
     *
     * @param tuSn                      SN号码
     * @param vendorCode                厂商代码
     * @param manageTranServerUrlPrefix 管理端接口前缀
     * @param superPosServerUrlPrefix   母POS管理服务前缀
     * @param waTranServerUrlPrefix     微信支付宝交易服务前缀
     * @param timeout                   超时时间
     * @param encryptionMachine         加密机实体
     * @param smartPosStorage           智能POS存储工具
     * @param bussId                    业务编号
     * @param sysNo                     前置系统标识
     */
    public VirtualSmartPos(String tuSn, String vendorCode, String manageTranServerUrlPrefix, String superPosServerUrlPrefix,
                           String waTranServerUrlPrefix, int timeout, IEncryption encryptionMachine, SmartPosStorage smartPosStorage,
                           String bussId, String sysNo) throws Exception {

        this.smartPosStorage = smartPosStorage;
        this.terminalAuth = new TerminalAuth(encryptionMachine);
        this.urls = new ServiceUrlInfo(vendorCode, tuSn, manageTranServerUrlPrefix, superPosServerUrlPrefix, waTranServerUrlPrefix);
        this.timeout = timeout;
        this.traceNoSeqGenerator = SequenceGeneratorDbSequenceUtil.createSequenceGeneratorDb("traceNo_" + terminalId);
        this.tuSn = tuSn;
        this.vendorCode = vendorCode;
        this.bussId = bussId;
        this.sysNo = sysNo;


        SmartPosStorage.SmartPosInfo smartPosInfo = this.smartPosStorage.getSmartPosInfo(vendorCode, tuSn);

        String lmkDek = smartPosInfo.lmkDek;
        String merchantId = smartPosInfo.merchantNo;
        String terminalId = smartPosInfo.terminalNo;
        String lmkTmk = smartPosInfo.lmkTmk;
        String lmkZek = smartPosInfo.zekInfo.lmkZek;
        String zekKeyInd = smartPosInfo.zekInfo.zekKeyInd;
        SuperPos superPos = this.smartPosStorage.getSuperPos(vendorCode, tuSn);

        if (lmkDek == null) {
            this.downloadInitializations(tuSn, vendorCode, superPos);
        } else {
            this.terminalAuth.setLmkDek(lmkDek);
        }

        if (lmkTmk == null) {
            this.smartPosStorage.saveNeedParamDownload(this.vendorCode, this.tuSn, true);
        } else {
            this.terminalAuth.setLmkTmk(lmkTmk);
        }

        if (lmkZek == null || merchantId == null || terminalId == null) {
            this.smartPosStorage.saveNeedSignInStatus(this.vendorCode, this.tuSn, true);
        } else {
            this.merchantId = merchantId;
            this.terminalId = terminalId;
            this.terminalAuth.setLmkZek(lmkZek);
            this.zekKeyInd = zekKeyInd;
        }
        this.checkSingIn();
        this.batchNo = this.smartPosStorage.getBatchNo(this.vendorCode, this.tuSn);

    }

    @Override
    public SmartPosTraner createTraner() throws Exception {
        this.checkSingIn();
        return new SmartPosTraner(this.merchantId, this.terminalId, this.urls, this.timeout, this.batchNo,
                this.terminalAuth, this.traceNoSeqGenerator, this.bussId, this.sysNo, this.checkSingInConsumer);
    }


    /**
     * 检查是否需要签名，在创建Traner时和创建虚拟智能Pos时会调用,方法会做签到和下载Tmk的操作
     *
     * @throws Exception 签到异常或者下载Tmk出错时，会抛出异常
     */
    private synchronized void checkSingIn() throws Exception {

        Terminal terminalInfo = this.smartPosStorage.getAllTerminalInfo(this.vendorCode, this.tuSn);

        if (terminalInfo.getNeedSignIn() == null || terminalInfo.getNeedSignIn()) {
            SmartPosTraner traner = new SmartPosTraner(this.merchantId, this.terminalId, this.urls, this.timeout, this.batchNo,
                    this.terminalAuth, this.traceNoSeqGenerator, this.bussId, this.sysNo, this.checkSingInConsumer);
            SingInResult singInResult = traner.singIn();
            if (singInResult == null) {
                singInResult = new SingInResult();
                singInResult.setSingInRetCode("9999");
                singInResult.setSingInRetMsg("签到失败");
            }

            if (singInResult.getDekZek() == null) {
                LOGGER.error("签到出错：Code：" + singInResult.getSingInRetCode() + ";Msg:" + singInResult.getSingInRetMsg());
                throw new SingInException(singInResult.getSingInRetCode(), singInResult.getSingInRetMsg());
            }

            this.terminalAuth.setLmkZekByDekZek(singInResult.getDekZek());
            this.terminalAuth.setZekKeyInd(singInResult.getZekKeyInd());
            this.merchantId = singInResult.getMerNo();
            this.terminalId = singInResult.getTermNo();
            this.zekKeyInd = singInResult.getZekKeyInd();

            //保存数据，且更新完数据以后，将NeedSignIn重置为false
            this.smartPosStorage.saveMerchantNoTerminalNoAndZek(this.vendorCode, this.tuSn, singInResult.getMerNo(),
                    singInResult.getTermNo(), this.terminalAuth.getLmkZek(), singInResult.getZekKeyInd());
            this.batchNo = this.smartPosStorage.updateBatchNo(this.vendorCode, this.tuSn);

        } else {
            //检查当前Zek与数据库中的Zek是否相同，如果不是，将同步数据库的Zek到当前内存中
            if (terminalInfo.getLmkZek() != null && !terminalInfo.getLmkZek().equals(this.terminalAuth.getLmkZek())) {
                this.terminalAuth.setLmkZek(terminalInfo.getLmkZek());
                this.zekKeyInd = terminalInfo.getZekKeyInd();
            }

            if (terminalInfo.getBatchNo() != null && !terminalInfo.getBatchNo().equals(this.batchNo)) {
                this.batchNo = terminalInfo.getBatchNo();
            }
        }

        if (terminalInfo.getNeedParamDownload() == null || terminalInfo.getNeedParamDownload()) {
            SmartPosTraner traner = new SmartPosTraner(this.merchantId, this.terminalId, this.urls, this.timeout, this.batchNo,
                    this.terminalAuth, this.traceNoSeqGenerator, this.bussId, this.sysNo, this.checkSingInConsumer);
            DownloadTmkResult downloadTmkResult = traner.paramDownload(this.vendorCode, this.tuSn, this.zekKeyInd);
            if (downloadTmkResult == null) {
                downloadTmkResult = new DownloadTmkResult();
                downloadTmkResult.setRetCode("9999");
                downloadTmkResult.setRetMsg("下载收单主密钥失败");
            }

            if (downloadTmkResult.getDekTmk() == null) {
                LOGGER.error("TMK下载出错：Code：" + downloadTmkResult.getRetCode() + ";Msg:" + downloadTmkResult.getRetMsg());
                throw new DownloadTmkException(downloadTmkResult.getRetCode(), downloadTmkResult.getRetMsg());
            }

            this.terminalAuth.setLmkTmkByDekTmk(downloadTmkResult.getDekTmk());

            //保存TMK,且更新完数据以后，将NeedParamDownload重置为false
            this.smartPosStorage.saveLmkTmk(vendorCode, tuSn, this.terminalAuth.getLmkTmk());
        } else {
            //检查当前内存Tmk与数据库中的Tmk是否相同，如果不是，将同步数据库中的Tmk到当前内存中
            if (terminalInfo.getLmkTmk() != null && !terminalInfo.getLmkTmk().equals(this.terminalAuth.getLmkTmk())) {
                this.terminalAuth.setLmkTmk(terminalInfo.getLmkTmk());
            }
        }

        TerminalPositionInfo positionInfo = terminalInfo.getPositionInfo();
        if (positionInfo != null) {
            if (positionInfo.getNeedUpload() == null || positionInfo.getNeedUpload()) {
                SmartPosTraner traner = new SmartPosTraner(this.merchantId, this.terminalId, this.urls, this.timeout, this.batchNo,
                        this.terminalAuth, this.traceNoSeqGenerator, this.bussId, this.sysNo, this.checkSingInConsumer);
                PositionUploadResult positionUploadResult = traner.positionUpload(positionInfo, this.zekKeyInd);

                if (positionUploadResult == null || !"0000".equalsIgnoreCase(positionUploadResult.getRetCode())) {
                    LOGGER.error("初始化位置上送失败，返回信息为：" + positionUploadResult);
                }

                this.smartPosStorage.saveNeedPositionInfoUpload(vendorCode, tuSn, false);
            }
        }

    }


    /**
     * 下载设备初始化参数方法
     *
     * @param sn         需要下载初始化参数的设备Sn
     * @param vendorCode 厂商代码
     * @param superPos   母POS信息
     * @throws Exception 下载异常
     */
    private synchronized void downloadInitializations(String sn, String vendorCode, SuperPos superPos) throws Exception {
        PrivateKey privateKey = null;

        if (superPos == null) {
            LOGGER.error("POS设备下载终端主密钥时，未发现母POS信息");
            throw new SuperPosIsNullException();
        }

        if (superPos.getPrivateKey() != null && !"".equals(superPos.getPrivateKey())) {
            try {
                privateKey = RsaSha1.getPrivateKeyByBase64Str(superPos.getPrivateKey());
            } catch (Exception e) {
                LOGGER.error("设备[" + sn + "]的母POS秘钥从数据库加载失败", e);
            }
        }

        if (privateKey == null) {
            try {
                String pfxFilePath = superPos.getPfxFilePath();
                String pfxPassword = superPos.getPfxPassword();
                privateKey = PfxCertUtil.getPrivateKey(pfxFilePath, pfxPassword);
            } catch (Exception e) {
                LOGGER.error("设备" + sn + "的母POS秘钥加载失败", e);
                throw new SuperPosIsNullException();
            }
        }

        SmartPosTraner traner = new SmartPosTraner(this.merchantId, this.terminalId, this.urls, this.timeout, this.batchNo,
                this.terminalAuth, this.traceNoSeqGenerator, this.bussId, this.sysNo, this.checkSingInConsumer);
        DownloadInitParameterResult initParameterResult = traner.downloadInitializations(superPos.getSn(), sn, vendorCode, privateKey);

        if (!"0000".equals(initParameterResult.getRetCode())) {
            //当下载初始化参数接口调用失败时，系统直接返回失败信息，并停止签到流程
            throw new DekErrorException();
        } else {
            this.terminalAuth.setLmkDekByZmkDek(initParameterResult.getDekZmk());

            //保存终端主密钥
            this.smartPosStorage.saveLmkDek(this.vendorCode, this.tuSn, this.terminalAuth.getLmkDek());
        }
    }


    public TerminalAuth getTerminalAuth() {
        return terminalAuth;
    }

}
