package com.yada.qrcode.payment.vspos.entity.send.manager.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * @author quhan
 */
public class PosRespMessage<T extends RespMsgBody> {

    @JSONField(name = "RetCode")
    private String retCode;

    @JSONField(name = "RetMsg")
    private String retMsg;

    @JSONField(name = "Body")
    private T body;

    public PosRespMessage() {
    }

    public PosRespMessage(String retCode, String retMsg, T body) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.body = body;
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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PosRespMessage<?> that = (PosRespMessage<?>) o;
        return Objects.equals(retCode, that.retCode) &&
                Objects.equals(retMsg, that.retMsg) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retCode, retMsg, body);
    }

    @Override
    public String toString() {
        return "PosRespMessage{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", body=" + body +
                '}';
    }
}
