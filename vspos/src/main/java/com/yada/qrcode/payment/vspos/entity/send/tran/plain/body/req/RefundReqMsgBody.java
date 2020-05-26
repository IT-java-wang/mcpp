package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * @author tandong
 * 智能pos退货请求
 */
public class RefundReqMsgBody extends AbstractTranResMsgBody {

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

    public RefundReqMsgBody() {
    }

    public RefundReqMsgBody(String merId, String termId) {
        super(merId, termId);
    }

    public RefundReqMsgBody(String merId, String termId, String payLs, String traceNo, String batchNo, String merOrderNo, String oldPayType, String oldBankDate, String oldOrderNo, String oldPayVounum, String refundAmt) {
        super(merId, termId, payLs, traceNo, batchNo);
        this.merOrderNo = merOrderNo;
        this.oldPayType = oldPayType;
        this.oldBankDate = oldBankDate;
        this.oldOrderNo = oldOrderNo;
        this.oldPayVounum = oldPayVounum;
        this.refundAmt = refundAmt;
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
        RefundReqMsgBody that = (RefundReqMsgBody) o;
        return Objects.equals(merOrderNo, that.merOrderNo) &&
                Objects.equals(oldPayType, that.oldPayType) &&
                Objects.equals(oldBankDate, that.oldBankDate) &&
                Objects.equals(oldOrderNo, that.oldOrderNo) &&
                Objects.equals(oldPayVounum, that.oldPayVounum) &&
                Objects.equals(refundAmt, that.refundAmt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), merOrderNo, oldPayType, oldBankDate, oldOrderNo, oldPayVounum, refundAmt);
    }

    @Override
    public String toString() {
        return "RefundReqMsgBody{" +
                "merOrderNo='" + merOrderNo + '\'' +
                ", oldPayType='" + oldPayType + '\'' +
                ", oldBankDate='" + oldBankDate + '\'' +
                ", oldOrderNo='" + oldOrderNo + '\'' +
                ", oldPayVounum='" + oldPayVounum + '\'' +
                ", refundAmt='" + refundAmt + '\'' +
                "} " + super.toString();
    }
}
