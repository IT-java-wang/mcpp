package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * 抽象的交易请求消息MsgBody
 *
 * @author quhan
 */
public abstract class AbstractTranResMsgBody implements MsgBody {

    /**
     * 商户号
     */
    @JSONField(name = "MerId")
    private String merId;

    /**
     * 终端号
     */
    @JSONField(name = "TermId")
    private String termId;

    /**
     * 前端唯一流水号
     */
    @JSONField(name = "PayLs")
    private String payLs;


    /**
     * 终端流水号
     */
    @JSONField(name = "TraceNo")
    private String traceNo;


    /**
     * 批次号
     */
    @JSONField(name = "BatchNo")
    private String batchNo;

    public AbstractTranResMsgBody() {
    }

    public AbstractTranResMsgBody(String merId, String termId) {
        this.merId = merId;
        this.termId = termId;
    }

    public AbstractTranResMsgBody(String merId, String termId, String payLs, String traceNo, String batchNo) {
        this.merId = merId;
        this.termId = termId;
        this.payLs = payLs;
        this.traceNo = traceNo;
        this.batchNo = batchNo;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getPayLs() {
        return payLs;
    }

    public void setPayLs(String payLs) {
        this.payLs = payLs;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractTranResMsgBody that = (AbstractTranResMsgBody) o;
        return Objects.equals(merId, that.merId) &&
                Objects.equals(termId, that.termId) &&
                Objects.equals(payLs, that.payLs) &&
                Objects.equals(traceNo, that.traceNo) &&
                Objects.equals(batchNo, that.batchNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merId, termId, payLs, traceNo, batchNo);
    }

    @Override
    public String toString() {
        return "AbstractTranResMsgBody{" +
                "merId='" + merId + '\'' +
                ", termId='" + termId + '\'' +
                ", payLs='" + payLs + '\'' +
                ", traceNo='" + traceNo + '\'' +
                ", batchNo='" + batchNo + '\'' +
                '}';
    }
}
