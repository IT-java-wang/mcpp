package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * @author tandong
 * 智能pos查询响应
 */
public class QueryRespMsgBody extends AbstractTranRespMsgBody {

    /**
     * 原支付方式    M M
     */
    @JSONField(name = "OldPayType")
    private String oldPayType;

    /**
     * 原交易编码    M M    被查询交易的交易编码
     */
    @JSONField(name = "OldTranId")
    private String oldTranId;

    /**
     * 原唯一流水号    M M    被查询交易的原唯一流水号
     */
    @JSONField(name = "OldPayLs")
    private String oldPayLs;

    /**
     * 原交易金额    M M
     */
    @JSONField(name = "OldTranAmt")
    private String oldTranAmt;

    /**
     * 原交易币种 M M
     * 取值:固定填 156
     */
    @JSONField(name = "OldCcyCode")
    private String oldCcyCode;

    /**
     * 原银行交易日期    C
     */
    @JSONField(name = "OldBankDate")
    private String oldBankDate;

    /**
     * 原银行交易时间    C
     */
    @JSONField(name = "OldBankTime")
    private String oldBankTime;

    /**
     * 原微信交易单号  C 当
     * OldPayType=
     * WEIX 时返回本域。
     */
    @JSONField(name = "OldTradeId")
    private String oldTradeId;

    /**
     * 原支付宝交易单号  C 当
     * OldPayType=
     * ZFBA 时返回本域。
     */
    @JSONField(name = "OldTradeNo")
    private String oldTradeNo;

    /**
     * 原银联付款凭证号   C 当
     * OldPayType=
     * UPAY 时时返回本域
     */
    @JSONField(name = "OldPayVounum")
    private String oldPayVounum;

    /**
     * 原银联支付订单号 C 当
     * OldPayType=
     * UPAY 时时返回本域
     */
    @JSONField(name = "OldPayNo")
    private String oldPayNo;

    /**
     * 原终端流水号
     */
    @JSONField(name = "OldTraceNo")
    private String oldTraceNo;

    /**
     * C原商户订单号
     */
    @JSONField(name = "OldOrderNo")
    private String oldOrderNo;

    /**
     * 原交易响应码
     */
    @JSONField(name = "OldRespCode")
    private String oldRespCode;

    /**
     * 原交易响应信息
     */
    @JSONField(name = "OldRespMsg")
    private String oldRespMsg;

    public QueryRespMsgBody() {
    }

    public String getOldRespCode() {
        return oldRespCode;
    }

    public void setOldRespCode(String oldRespCode) {
        this.oldRespCode = oldRespCode;
    }

    public String getOldRespMsg() {
        return oldRespMsg;
    }

    public void setOldRespMsg(String oldRespMsg) {
        this.oldRespMsg = oldRespMsg;
    }

    public String getOldPayType() {
        return oldPayType;
    }

    public void setOldPayType(String oldPayType) {
        this.oldPayType = oldPayType;
    }

    public String getOldTranId() {
        return oldTranId;
    }

    public void setOldTranId(String oldTranId) {
        this.oldTranId = oldTranId;
    }

    public String getOldPayLs() {
        return oldPayLs;
    }

    public void setOldPayLs(String oldPayLs) {
        this.oldPayLs = oldPayLs;
    }

    public String getOldTranAmt() {
        return oldTranAmt;
    }

    public void setOldTranAmt(String oldTranAmt) {
        this.oldTranAmt = oldTranAmt;
    }

    public String getOldCcyCode() {
        return oldCcyCode;
    }

    public void setOldCcyCode(String oldCcyCode) {
        this.oldCcyCode = oldCcyCode;
    }

    public String getOldBankDate() {
        return oldBankDate;
    }

    public void setOldBankDate(String oldBankDate) {
        this.oldBankDate = oldBankDate;
    }

    public String getOldBankTime() {
        return oldBankTime;
    }

    public void setOldBankTime(String oldBankTime) {
        this.oldBankTime = oldBankTime;
    }

    public String getOldTradeId() {
        return oldTradeId;
    }

    public void setOldTradeId(String oldTradeId) {
        this.oldTradeId = oldTradeId;
    }

    public String getOldTradeNo() {
        return oldTradeNo;
    }

    public void setOldTradeNo(String oldTradeNo) {
        this.oldTradeNo = oldTradeNo;
    }

    public String getOldPayVounum() {
        return oldPayVounum;
    }

    public void setOldPayVounum(String oldPayVounum) {
        this.oldPayVounum = oldPayVounum;
    }

    public String getOldPayNo() {
        return oldPayNo;
    }

    public void setOldPayNo(String oldPayNo) {
        this.oldPayNo = oldPayNo;
    }

    public String getOldTraceNo() {
        return oldTraceNo;
    }

    public void setOldTraceNo(String oldTraceNo) {
        this.oldTraceNo = oldTraceNo;
    }

    public String getOldOrderNo() {
        return oldOrderNo;
    }

    public void setOldOrderNo(String oldOrderNo) {
        this.oldOrderNo = oldOrderNo;
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
        QueryRespMsgBody that = (QueryRespMsgBody) o;
        return Objects.equals(oldPayType, that.oldPayType) &&
                Objects.equals(oldTranId, that.oldTranId) &&
                Objects.equals(oldPayLs, that.oldPayLs) &&
                Objects.equals(oldTranAmt, that.oldTranAmt) &&
                Objects.equals(oldCcyCode, that.oldCcyCode) &&
                Objects.equals(oldBankDate, that.oldBankDate) &&
                Objects.equals(oldBankTime, that.oldBankTime) &&
                Objects.equals(oldTradeId, that.oldTradeId) &&
                Objects.equals(oldTradeNo, that.oldTradeNo) &&
                Objects.equals(oldPayVounum, that.oldPayVounum) &&
                Objects.equals(oldPayNo, that.oldPayNo) &&
                Objects.equals(oldTraceNo, that.oldTraceNo) &&
                Objects.equals(oldOrderNo, that.oldOrderNo) &&
                Objects.equals(oldRespCode, that.oldRespCode) &&
                Objects.equals(oldRespMsg, that.oldRespMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), oldPayType, oldTranId, oldPayLs, oldTranAmt, oldCcyCode, oldBankDate, oldBankTime, oldTradeId, oldTradeNo, oldPayVounum, oldPayNo, oldTraceNo, oldOrderNo, oldRespCode, oldRespMsg);
    }

    @Override
    public String toString() {
        return "QueryRespMsgBody{" +
                "oldPayType='" + oldPayType + '\'' +
                ", oldTranId='" + oldTranId + '\'' +
                ", oldPayLs='" + oldPayLs + '\'' +
                ", oldTranAmt='" + oldTranAmt + '\'' +
                ", oldCcyCode='" + oldCcyCode + '\'' +
                ", oldBankDate='" + oldBankDate + '\'' +
                ", oldBankTime='" + oldBankTime + '\'' +
                ", oldTradeId='" + oldTradeId + '\'' +
                ", oldTradeNo='" + oldTradeNo + '\'' +
                ", oldPayVounum='" + oldPayVounum + '\'' +
                ", oldPayNo='" + oldPayNo + '\'' +
                ", oldTraceNo='" + oldTraceNo + '\'' +
                ", oldOrderNo='" + oldOrderNo + '\'' +
                ", oldRespCode='" + oldRespCode + '\'' +
                ", oldRespMsg='" + oldRespMsg + '\'' +
                "} " + super.toString();
    }
}
