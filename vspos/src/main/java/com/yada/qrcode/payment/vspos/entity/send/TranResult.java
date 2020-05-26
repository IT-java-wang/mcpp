package com.yada.qrcode.payment.vspos.entity.send;

import com.yada.qrcode.payment.vspos.entity.send.base.ReqMessage;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.RespMessage;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.req.AbstractTranResMsgBody;
import com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp.AbstractTranRespMsgBody;

import java.util.Objects;

/**
 * 交易结果
 *
 * @author quhan
 */
public class TranResult<ReqMsgBody extends AbstractTranResMsgBody, RespMsgBody extends AbstractTranRespMsgBody> {

    /**
     * 网络响应码
     * 1、2000 通用错误返回码
     * 2、2002 交易授权码错误
     * 3、2003 加密机错误
     */
    private String netCode;

    /**
     * 网络响应信息
     */
    private String netMsg;

    /**
     * 请求信息
     */
    private ReqMessage<ReqMsgBody> reqMessage;

    /**
     * 响应信息
     */
    private RespMessage<RespMsgBody> respMessage;

    public TranResult() {
    }

    public TranResult(String netCode, String netMsg) {
        this.netCode = netCode;
        this.netMsg = netMsg;
    }

    public TranResult(String netCode, String netMsg, ReqMessage<ReqMsgBody> reqMessage, RespMessage<RespMsgBody> respMessage) {
        this.netCode = netCode;
        this.netMsg = netMsg;
        this.reqMessage = reqMessage;
        this.respMessage = respMessage;
    }

    public TranResult(ReqMessage<ReqMsgBody> reqMessage, RespMessage<RespMsgBody> respMessage) {
        this.netCode = "1000";
        this.netMsg = "成功";
        this.reqMessage = reqMessage;
        this.respMessage = respMessage;
    }

    public String getNetCode() {
        return netCode;
    }

    public void setNetCode(String netCode) {
        this.netCode = netCode;
    }

    public String getNetMsg() {
        return netMsg;
    }

    public void setNetMsg(String netMsg) {
        this.netMsg = netMsg;
    }

    public ReqMessage<ReqMsgBody> getReqMessage() {
        return reqMessage;
    }

    public void setReqMessage(ReqMessage<ReqMsgBody> reqMessage) {
        this.reqMessage = reqMessage;
    }

    public RespMessage<RespMsgBody> getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(RespMessage<RespMsgBody> respMessage) {
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
        TranResult<?, ?> that = (TranResult<?, ?>) o;
        return Objects.equals(netCode, that.netCode) &&
                Objects.equals(netMsg, that.netMsg) &&
                Objects.equals(reqMessage, that.reqMessage) &&
                Objects.equals(respMessage, that.respMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netCode, netMsg, reqMessage, respMessage);
    }

    @Override
    public String toString() {
        return "TranResult{" +
                "netCode='" + netCode + '\'' +
                ", netMsg='" + netMsg + '\'' +
                ", reqMessage=" + reqMessage +
                ", respMessage=" + respMessage +
                '}';
    }
}
