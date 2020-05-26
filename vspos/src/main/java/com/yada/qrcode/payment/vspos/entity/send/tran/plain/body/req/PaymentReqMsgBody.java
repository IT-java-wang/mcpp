package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.req;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * @author quhan
 */
public class PaymentReqMsgBody extends AbstractTranResMsgBody {

    /**
     * 支付方式
     */
    @JSONField(name = "PayType")
    private String payType;

    /**
     * 支付授权码
     */
    @JSONField(name = "AuthCode")
    private String authCode;

    /**
     * 交易金额
     */
    @JSONField(name = "TranAmt")
    private String tranAmt;

    /**
     * 币种GB2659-94
     */
    @JSONField(name = "CcyCode")
    private String ccyCode;

    /**
     * 外部商户订单号
     */
    @JSONField(name = "MerOrderNo")
    private String merOrderNo;

    /**
     * 柜员号
     */
    @JSONField(name = "OperNo")
    private String operNo;

    /**
     * 支付有效时间，最短为1，最长为10，小于1分钟按1分钟计算，大于10分钟按10分钟计算
     */
    @JSONField(name = "PayValidTime")
    private String payValidTime;


    public PaymentReqMsgBody() {
    }

    public PaymentReqMsgBody(String merId, String termId) {
        super(merId, termId);
    }

    public PaymentReqMsgBody(String merId, String termId, String payLs, String traceNo, String batchNo, String payType, String authCode, String tranAmt, String ccyCode, String merOrderNo, String operNo, String payValidTime) {
        super(merId, termId, payLs, traceNo, batchNo);
        this.payType = payType;
        this.authCode = authCode;
        this.tranAmt = tranAmt;
        this.ccyCode = ccyCode;
        this.merOrderNo = merOrderNo;
        this.operNo = operNo;
        this.payValidTime = payValidTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getCcyCode() {
        return ccyCode;
    }

    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    public String getOperNo() {
        return operNo;
    }

    public void setOperNo(String operNo) {
        this.operNo = operNo;
    }

    public String getPayValidTime() {
        return payValidTime;
    }

    public void setPayValidTime(String payValidTime) {
        this.payValidTime = payValidTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PaymentReqMsgBody that = (PaymentReqMsgBody) o;
        return Objects.equals(payType, that.payType) &&
                Objects.equals(authCode, that.authCode) &&
                Objects.equals(tranAmt, that.tranAmt) &&
                Objects.equals(ccyCode, that.ccyCode) &&
                Objects.equals(merOrderNo, that.merOrderNo) &&
                Objects.equals(operNo, that.operNo) &&
                Objects.equals(payValidTime, that.payValidTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), payType, authCode, tranAmt, ccyCode, merOrderNo, operNo, payValidTime);
    }

    @Override
    public String toString() {
        return "PaymentReqMsgBody{" +
                "payType='" + payType + '\'' +
                ", authCode='" + authCode + '\'' +
                ", tranAmt='" + tranAmt + '\'' +
                ", ccyCode='" + ccyCode + '\'' +
                ", merOrderNo='" + merOrderNo + '\'' +
                ", operNo='" + operNo + '\'' +
                ", payValidTime='" + payValidTime + '\'' +
                '}';
    }
}
