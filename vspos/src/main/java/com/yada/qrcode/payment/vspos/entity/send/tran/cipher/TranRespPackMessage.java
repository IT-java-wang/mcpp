package com.yada.qrcode.payment.vspos.entity.send.tran.cipher;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * 交易响应信息，用于接收返回值（密文）
 *
 * @author quhan
 */
public class TranRespPackMessage {

    /**
     * 前置返回响应吗
     */
    @JSONField(name = "RetCode")
    private String retCode;

    /**
     * 前置返回响应信息
     */
    @JSONField(name = "RetMsg")
    private String retMsg;

    /**
     * 业务报文体
     */
    @JSONField(name = "MsgData")
    private String msgData;

    public TranRespPackMessage() {
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TranRespPackMessage that = (TranRespPackMessage) o;
        return Objects.equals(retCode, that.retCode) &&
                Objects.equals(retMsg, that.retMsg) &&
                Objects.equals(msgData, that.msgData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retCode, retMsg, msgData);
    }

    @Override
    public String toString() {
        return "TranRespPackMessage{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", msgData='" + msgData + '\'' +
                '}';
    }
}
