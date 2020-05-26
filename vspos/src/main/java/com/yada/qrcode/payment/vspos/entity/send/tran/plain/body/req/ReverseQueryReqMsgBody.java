package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * @author tandong
 * 智能pos查询请求
 */
public class ReverseQueryReqMsgBody extends AbstractTranResMsgBody {

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
     * 柜员号
     */
    @JSONField(name = "OperNo")
    private String operNo;

    public ReverseQueryReqMsgBody() {
    }

    public ReverseQueryReqMsgBody(String merId, String termId) {
        super(merId, termId);
    }

    public ReverseQueryReqMsgBody(String merId, String termId, String payLs, String traceNo, String batchNo, String oldPayType, String oldTranId, String oldPayLs, String oldTranAmt, String oldCcyCode, String operNo) {
        super(merId, termId, payLs, traceNo, batchNo);
        this.oldPayType = oldPayType;
        this.oldTranId = oldTranId;
        this.oldPayLs = oldPayLs;
        this.oldTranAmt = oldTranAmt;
        this.oldCcyCode = oldCcyCode;
        this.operNo = operNo;
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
        ReverseQueryReqMsgBody that = (ReverseQueryReqMsgBody) o;
        return Objects.equals(oldPayType, that.oldPayType) &&
                Objects.equals(oldTranId, that.oldTranId) &&
                Objects.equals(oldPayLs, that.oldPayLs) &&
                Objects.equals(oldTranAmt, that.oldTranAmt) &&
                Objects.equals(oldCcyCode, that.oldCcyCode) &&
                Objects.equals(operNo, that.operNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), oldPayType, oldTranId, oldPayLs, oldTranAmt, oldCcyCode, operNo);
    }

    @Override
    public String toString() {
        return "ReverseQueryReqMsgBody{" +
                "oldPayType='" + oldPayType + '\'' +
                ", oldTranId='" + oldTranId + '\'' +
                ", oldPayLs='" + oldPayLs + '\'' +
                ", oldTranAmt='" + oldTranAmt + '\'' +
                ", oldCcyCode='" + oldCcyCode + '\'' +
                ", operNo='" + operNo + '\'' +
                "} " + super.toString();
    }
}
