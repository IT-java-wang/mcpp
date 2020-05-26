package com.yada.qrcode.payment.vspos.entity.send.tran.plain.body.resp;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * 抽象的交易响应消息MsgBody
 *
 * @author quhan
 */
public abstract class AbstractTranRespMsgBody implements MsgBody {

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


    /**
     * 响应码
     * M 交易结果代码, 参考附录 4响应码
     */
    @JSONField(name = "RespCode")
    private String respCode;

    /**
     * 响应信息
     * M 交易结果描述, APP
     * 端回显此信息
     */
    @JSONField(name = "RespMsg")
    private String respMsg;


    /**
     * 银行交易日期
     * C 银行返回的交易日期(成功时必返)
     */
    @JSONField(name = "BankDate")
    private String bankDate;

    /**
     * 银行交易时间
     * C 银行返回的交易时间(成功时必返)
     */
    @JSONField(name = "BankTime")
    private String bankTime;

    public AbstractTranRespMsgBody() {
    }

    public AbstractTranRespMsgBody(String merId, String termId) {
        this.merId = merId;
        this.termId = termId;
    }

    public AbstractTranRespMsgBody(String merId, String termId, String payLs, String traceNo, String batchNo, String respCode, String respMsg, String bankDate, String bankTime) {
        this.merId = merId;
        this.termId = termId;
        this.payLs = payLs;
        this.traceNo = traceNo;
        this.batchNo = batchNo;
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.bankDate = bankDate;
        this.bankTime = bankTime;
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

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getBankDate() {
        return bankDate;
    }

    public void setBankDate(String bankDate) {
        this.bankDate = bankDate;
    }

    public String getBankTime() {
        return bankTime;
    }

    public void setBankTime(String bankTime) {
        this.bankTime = bankTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractTranRespMsgBody that = (AbstractTranRespMsgBody) o;
        return Objects.equals(merId, that.merId) &&
                Objects.equals(termId, that.termId) &&
                Objects.equals(payLs, that.payLs) &&
                Objects.equals(traceNo, that.traceNo) &&
                Objects.equals(batchNo, that.batchNo) &&
                Objects.equals(respCode, that.respCode) &&
                Objects.equals(respMsg, that.respMsg) &&
                Objects.equals(bankDate, that.bankDate) &&
                Objects.equals(bankTime, that.bankTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merId, termId, payLs, traceNo, batchNo, respCode, respMsg, bankDate, bankTime);
    }

    @Override
    public String toString() {
        return "AbstractTranRespMsgBody{" +
                "merId='" + merId + '\'' +
                ", termId='" + termId + '\'' +
                ", payLs='" + payLs + '\'' +
                ", traceNo='" + traceNo + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", respCode='" + respCode + '\'' +
                ", respMsg='" + respMsg + '\'' +
                ", bankDate='" + bankDate + '\'' +
                ", bankTime='" + bankTime + '\'' +
                '}';
    }
}
