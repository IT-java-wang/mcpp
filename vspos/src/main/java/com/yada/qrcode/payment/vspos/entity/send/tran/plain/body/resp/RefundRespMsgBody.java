package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * @author tandong
 * 智能pos退货响应
 */
public class RefundRespMsgBody extends AbstractTranRespMsgBody {

    /**
     * 外部商户订单号 C C
     * 请求:如前端系统存在此域则上送;
     * 回应:同请求
     */
    @JSONField(name = "MerOrderNo")
    private String merOrderNo;

    /**
     * 原支付方式M M
     */
    @JSONField(name = "OldPayType")
    private String oldPayType;

    /**
     * 原银行交易日期M M
     * 原消费返回的银行交易日期
     */
    @JSONField(name = "OldBankDate")
    private String oldBankDate;

    /**
     * 原商户订单号 C C
     * 当OldPayType 是
     * ZFBA 或
     * WEIX 时
     * 此域必填
     */
    @JSONField(name = "OldOrderNo")
    private String oldOrderNo;

    /**
     * 原付款凭证号C C
     * 原交易的付款凭证号。当
     * OldPayType 是
     * UPAY 时此域必填
     */
    @JSONField(name = "OldPayVounum")
    private String oldPayVounum;

    /**
     * 退货金额
     */
    @JSONField(name = "RefundAmt")
    private String refundAmt;

    /**
     * 当交易成功时返回此域
     * 商户退货订单号 C
     * (当 OldPayType 是 ZFBA 或 WEIX
     * 时有值)}
     */
    @JSONField(name = "RefundOrderNo")
    private String refundOrderNo;


    public RefundRespMsgBody() {
    }

    public RefundRespMsgBody(String merId, String termId) {
        super(merId, termId);
    }

    public RefundRespMsgBody(String merId, String termId, String payLs, String traceNo, String batchNo, String respCode, String respMsg, String bankDate, String bankTime, String merOrderNo, String oldPayType, String oldBankDate, String oldOrderNo, String oldPayVounum, String refundAmt, String refundOrderNo) {
        super(merId, termId, payLs, traceNo, batchNo, respCode, respMsg, bankDate, bankTime);
        this.merOrderNo = merOrderNo;
        this.oldPayType = oldPayType;
        this.oldBankDate = oldBankDate;
        this.oldOrderNo = oldOrderNo;
        this.oldPayVounum = oldPayVounum;
        this.refundAmt = refundAmt;
        this.refundOrderNo = refundOrderNo;
    }

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    public String getOldPayType() {
        return oldPayType;
    }

    public void setOldPayType(String oldPayType) {
        this.oldPayType = oldPayType;
    }

    public String getOldBankDate() {
        return oldBankDate;
    }

    public void setOldBankDate(String oldBankDate) {
        this.oldBankDate = oldBankDate;
    }

    public String getOldOrderNo() {
        return oldOrderNo;
    }

    public void setOldOrderNo(String oldOrderNo) {
        this.oldOrderNo = oldOrderNo;
    }

    public String getOldPayVounum() {
        return oldPayVounum;
    }

    public void setOldPayVounum(String oldPayVounum) {
        this.oldPayVounum = oldPayVounum;
    }

    public String getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(String refundAmt) {
        this.refundAmt = refundAmt;
    }

    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo;
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
        RefundRespMsgBody that = (RefundRespMsgBody) o;
        return Objects.equals(merOrderNo, that.merOrderNo) &&
                Objects.equals(oldPayType, that.oldPayType) &&
                Objects.equals(oldBankDate, that.oldBankDate) &&
                Objects.equals(oldOrderNo, that.oldOrderNo) &&
                Objects.equals(oldPayVounum, that.oldPayVounum) &&
                Objects.equals(refundAmt, that.refundAmt) &&
                Objects.equals(refundOrderNo, that.refundOrderNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), merOrderNo, oldPayType, oldBankDate, oldOrderNo, oldPayVounum, refundAmt, refundOrderNo);
    }

    @Override
    public String toString() {
        return "RefundRespMsgBody{" +
                "merOrderNo='" + merOrderNo + '\'' +
                ", oldPayType='" + oldPayType + '\'' +
                ", oldBankDate='" + oldBankDate + '\'' +
                ", oldOrderNo='" + oldOrderNo + '\'' +
                ", oldPayVounum='" + oldPayVounum + '\'' +
                ", refundAmt='" + refundAmt + '\'' +
                ", refundOrderNo='" + refundOrderNo + '\'' +
                "} " + super.toString();
    }
}

