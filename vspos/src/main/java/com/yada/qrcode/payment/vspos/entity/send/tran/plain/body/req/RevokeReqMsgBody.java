package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * @author tandong
 * Spos撤销请求
 */
public class RevokeReqMsgBody extends AbstractTranResMsgBody {


    /**
     * 外部商户订单号C C
     */
    @JSONField(name = "MerOrderNo")
    private String merOrderNo;

    /**
     * 原支付方式M M
     */
    @JSONField(name = "OldPayType")
    private String oldPayType;

    /**
     * 原交易编码 M M
     * 原交易编码
     */
    @JSONField(name = "OldTranId")
    private String oldTranId;

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
     * 柜员号
     */
    @JSONField(name = "OperNo")
    private String operNo;

    public RevokeReqMsgBody() {
    }

    public RevokeReqMsgBody(String merId, String termId) {
        super(merId, termId);
    }

    public RevokeReqMsgBody(String merId, String termId, String payLs, String traceNo, String batchNo, String merOrderNo, String oldPayType, String oldTranId, String oldOrderNo, String oldPayVounum, String operNo) {
        super(merId, termId, payLs, traceNo, batchNo);
        this.merOrderNo = merOrderNo;
        this.oldPayType = oldPayType;
        this.oldTranId = oldTranId;
        this.oldOrderNo = oldOrderNo;
        this.oldPayVounum = oldPayVounum;
        this.operNo = operNo;
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

    public String getOldTranId() {
        return oldTranId;
    }

    public void setOldTranId(String oldTranId) {
        this.oldTranId = oldTranId;
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

    public String getOperNo() {
        return operNo;
    }

    public void setOperNo(String operNo) {
        this.operNo = operNo;
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
        RevokeReqMsgBody that = (RevokeReqMsgBody) o;
        return Objects.equals(merOrderNo, that.merOrderNo) &&
                Objects.equals(oldPayType, that.oldPayType) &&
                Objects.equals(oldTranId, that.oldTranId) &&
                Objects.equals(oldOrderNo, that.oldOrderNo) &&
                Objects.equals(oldPayVounum, that.oldPayVounum) &&
                Objects.equals(operNo, that.operNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), merOrderNo, oldPayType, oldTranId, oldOrderNo, oldPayVounum, operNo);
    }

    @Override
    public String toString() {
        return "RevokeReqMsgBody{" +
                "merOrderNo='" + merOrderNo + '\'' +
                ", oldPayType='" + oldPayType + '\'' +
                ", oldTranId='" + oldTranId + '\'' +
                ", oldOrderNo='" + oldOrderNo + '\'' +
                ", oldPayVounum='" + oldPayVounum + '\'' +
                ", operNo='" + operNo + '\'' +
                "} " + super.toString();
    }
}
