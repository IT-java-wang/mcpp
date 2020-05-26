package com.yada.qrcode.payment.vspos.entity.send;

import com.yada.qrcode.payment.vspos.entity.enums.SendResultEnum;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.RespMessage;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp.AbstractTranRespMsgBody;

import java.util.Objects;

/**
 * 交易发送结果
 *
 * @author quhan
 */
public class SendResult<TranResp extends AbstractTranRespMsgBody> {
    /**
     * 用于标志交易状体
     */
    private SendResultEnum sendResultEnum;

    /**
     * 如果 sendResultEnum 不是 OK 状态，则这里需要带回去错误信息
     */
    private String errorMsg;

    /**
     * 响应消息
     */
    private RespMessage<TranResp> respMessage;


    public SendResult() {
    }

    public SendResult(RespMessage<TranResp> respMessage) {
        this.sendResultEnum = SendResultEnum.OK;
        this.errorMsg = null;
        this.respMessage = respMessage;
    }

    public SendResult(String errorMsg) {
        this.sendResultEnum = SendResultEnum.FAILED;
        this.errorMsg = errorMsg;
    }

    public SendResult(SendResultEnum sendResultEnum, String errorMsg) {
        this.sendResultEnum = sendResultEnum;
        this.errorMsg = errorMsg;
    }

    public SendResult(SendResultEnum sendResultEnum, String errorMsg, RespMessage<TranResp> respMessage) {
        this.sendResultEnum = sendResultEnum;
        this.errorMsg = errorMsg;
        this.respMessage = respMessage;
    }

    public SendResultEnum getSendResultEnum() {
        return sendResultEnum;
    }

    public void setSendResultEnum(SendResultEnum sendResultEnum) {
        this.sendResultEnum = sendResultEnum;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public RespMessage<TranResp> getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(RespMessage<TranResp> respMessage) {
        this.respMessage = respMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SendResult<?> that = (SendResult<?>) o;
        return sendResultEnum == that.sendResultEnum &&
                Objects.equals(errorMsg, that.errorMsg) &&
                Objects.equals(respMessage, that.respMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendResultEnum, errorMsg, respMessage);
    }

    @Override
    public String toString() {
        return "SendResult{" +
                "sendResultEnum=" + sendResultEnum +
                ", errorMsg='" + errorMsg + '\'' +
                ", respMessage=" + respMessage +
                '}';
    }
}
