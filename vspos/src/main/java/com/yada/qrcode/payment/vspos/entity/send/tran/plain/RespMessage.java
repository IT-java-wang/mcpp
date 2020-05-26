package com.yada.qrcode.payment.vspos.entity.send.tran.plain;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp.AbstractTranRespMsgBody;

import java.util.Objects;

/**
 * 智能Pos平台响应的数据明文
 *
 * @author quhan
 */
public class RespMessage<T extends AbstractTranRespMsgBody> {

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
     * 业务响应体
     */
    @JSONField(name = "MsgData")
    private T msgData;

    public RespMessage() {
    }

    public RespMessage(String retCode, String retMsg, T msgData) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.msgData = msgData;
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

    public T getMsgData() {
        return msgData;
    }

    public void setMsgData(T msgData) {
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
        RespMessage<?> that = (RespMessage<?>) o;
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
        return "RespMsgData{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", msgData=" + msgData +
                '}';
    }
}
