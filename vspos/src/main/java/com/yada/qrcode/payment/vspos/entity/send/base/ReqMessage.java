package com.yada.qrcode.payment.vspos.entity.send.base;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * 向智能POS请求的数据明文
 *
 * @author quhan
 */
public class ReqMessage<T extends MsgBody> {

    /**
     * 报文头
     */
    @JSONField(name = "MsgHeader")
    private MsgHeader msgHeader;

    /**
     * 报文体
     */
    @JSONField(name = "MsgBody")
    private T msgBody;

    public ReqMessage() {
    }

    public ReqMessage(MsgHeader msgHeader, T msgBody) {
        this.msgHeader = msgHeader;
        this.msgBody = msgBody;
    }

    public MsgHeader getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(MsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }

    public T getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(T msgBody) {
        this.msgBody = msgBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReqMessage<?> that = (ReqMessage<?>) o;
        return Objects.equals(msgHeader, that.msgHeader) &&
                Objects.equals(msgBody, that.msgBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgHeader, msgBody);
    }

    @Override
    public String toString() {
        return "ReqMessage{" +
                "msgHeader=" + msgHeader +
                ", msgBody=" + msgBody +
                '}';
    }
}
