package com.yada.qrcode.payment.vspos.spos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yada.qrcode.payment.common.http.HttpClient;
import com.yada.qrcode.payment.vspos.TerminalAuth;
import com.yada.qrcode.payment.vspos.entity.enums.SendResultEnum;
import com.yada.qrcode.payment.vspos.entity.send.SendResult;
import com.yada.qrcode.payment.vspos.entity.send.base.ReqMessage;
import com.yada.qrcode.payment.vspos.entity.send.tran.cipher.TranReqPackMessage;
import com.yada.qrcode.payment.vspos.entity.send.tran.cipher.TranRespPackMessage;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.RespMessage;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp.AbstractTranRespMsgBody;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * 发送交易方法
 *
 * @author quhan
 */
public class SendTran {


    private static Logger LOGGER = LoggerFactory.getLogger(SendTran.class);


    /**
     * 消息头
     */
    private static HashMap<String, String> headers = new HashMap<>();


    static {
        headers.put("Content-type", "application/json;charset=utf-8");
        headers.put("Connection", "Keep-Alive");
        headers.put("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
    }

    public static <T extends AbstractTranRespMsgBody> SendResult<T> sendTran(String url, TerminalAuth terminalAuth,
                                                                             ReqMessage reqMessage, int timeout, Class<T> clazz) {

        SendResult<T> sendResult = new SendResult<>();

        TranReqPackMessage tranReqPackMessage = new TranReqPackMessage();
        tranReqPackMessage.setsPosVersion("2.0");
        tranReqPackMessage.setZekKeyInd(terminalAuth.getLmkZek());
        tranReqPackMessage.setMsgType("json");
        tranReqPackMessage.setZekKeyInd(terminalAuth.getZekKeyInd());

        byte[] encoding;
        try {
            LOGGER.info("交易请求MsgData数据：" + JSON.toJSONString(reqMessage, SerializerFeature.DisableCircularReferenceDetect));
            encoding = terminalAuth.encoding(JSON.toJSONString(reqMessage, SerializerFeature.DisableCircularReferenceDetect));
        } catch (Exception e) {
            LOGGER.error("加密交易请求信息加密机连接错误：", e);
            sendResult.setSendResultEnum(SendResultEnum.MACHINE_ERROR);
            sendResult.setErrorMsg("加密机连接错误");
            return sendResult;
        }
        String hexEncodeMsgData = Hex.encodeHexString(encoding);
        tranReqPackMessage.setMsgData(hexEncodeMsgData);

        TranRespPackMessage tranRespPackMessage = null;
        try {
            String response = sendTran(url, JSON.toJSONString(tranReqPackMessage, SerializerFeature.DisableCircularReferenceDetect), timeout);
            tranRespPackMessage = JSON.parseObject(response, TranRespPackMessage.class);
        } catch (Exception e) {
            LOGGER.error("交易请求网络请求失败", e);
            sendResult.setSendResultEnum(SendResultEnum.NET_ERROR);
            sendResult.setErrorMsg("网络请求失败");
            return sendResult;
        }

        String respMsgData = tranRespPackMessage.getMsgData();
        String respMsgDataStr;

        RespMessage<T> respMessage = new RespMessage<>();
        respMessage.setRetCode(tranRespPackMessage.getRetCode());
        respMessage.setRetMsg(tranRespPackMessage.getRetMsg());

        if (respMsgData != null) {
            try {
                respMsgDataStr = new String(terminalAuth.decoding(respMsgData));
                LOGGER.info("解密交易响应MsgData数据：" + respMsgDataStr);
            } catch (Exception e) {
                LOGGER.error("解密交易响应信息解密发生错误", e);

                sendResult.setSendResultEnum(SendResultEnum.MACHINE_ERROR);
                sendResult.setErrorMsg("响应报文解密错误");
                return sendResult;
            }

            T paymentRespMsgBody = JSON.parseObject(respMsgDataStr, clazz);

            respMessage.setMsgData(paymentRespMsgBody);
        } else {
            LOGGER.error("响应报文中MsgData为空，交易失败，RetCode：" + tranRespPackMessage.getRetCode() + ";RetMsg：" + tranRespPackMessage.getRetMsg());
        }

        sendResult = new SendResult<>(respMessage);

        return sendResult;
    }


    /**
     * 发送管理类交易方法
     *
     * @param url        请求地址
     * @param reqMsgData Pos请求信息
     * @return 返回数据
     */
    public static String sendTran(String url, ReqMessage reqMsgData, int timeout) throws IOException {
        return sendTran(url, JSON.toJSONString(reqMsgData, SerializerFeature.DisableCircularReferenceDetect), timeout);
    }


    /**
     * 发送交易方法
     *
     * @param url        请求地址
     * @param reqMsgData Pos请求信息
     * @return 返回数据
     */
    private static String sendTran(String url, String reqMsgData, int timeout) throws IOException {

        return HttpClient.sendPost(url, reqMsgData, headers, null, timeout, timeout);
    }


}
