package com.yada.qrcode.payment.vspos.entity.send.manager.result;

import java.util.Objects;

/**
 * 签到结果
 *
 * @author quhan
 */
public class SingInResult {

    /**
     * 响应码，判断是否签到成功，签到成功返回 0000
     */
    private String singInRetCode;

    /**
     * 签到交易响应信息
     */
    private String singInRetMsg;

    /**
     * 商户号
     */
    private String merNo;

    /**
     * 终端号
     */
    private String termNo;

    /**
     * 受加密机保护的工作密钥
     */
    private String dekZek;

    /**
     * 密钥索引
     */
    private String zekKeyInd;

    public SingInResult() {
    }

    public SingInResult(String singInRetCode, String singInRetMsg, String merNo, String termNo, String dekZek, String zekKeyInd) {
        this.singInRetCode = singInRetCode;
        this.singInRetMsg = singInRetMsg;
        this.merNo = merNo;
        this.termNo = termNo;
        this.dekZek = dekZek;
        this.zekKeyInd = zekKeyInd;
    }

    public String getSingInRetCode() {
        return singInRetCode;
    }

    public void setSingInRetCode(String singInRetCode) {
        this.singInRetCode = singInRetCode;
    }

    public String getSingInRetMsg() {
        return singInRetMsg;
    }

    public void setSingInRetMsg(String singInRetMsg) {
        this.singInRetMsg = singInRetMsg;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public String getDekZek() {
        return dekZek;
    }

    public void setDekZek(String dekZek) {
        this.dekZek = dekZek;
    }

    public String getZekKeyInd() {
        return zekKeyInd;
    }

    public void setZekKeyInd(String zekKeyInd) {
        this.zekKeyInd = zekKeyInd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SingInResult that = (SingInResult) o;
        return Objects.equals(singInRetCode, that.singInRetCode) &&
                Objects.equals(singInRetMsg, that.singInRetMsg) &&
                Objects.equals(merNo, that.merNo) &&
                Objects.equals(termNo, that.termNo) &&
                Objects.equals(dekZek, that.dekZek) &&
                Objects.equals(zekKeyInd, that.zekKeyInd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(singInRetCode, singInRetMsg, merNo, termNo, dekZek, zekKeyInd);
    }

    @Override
    public String toString() {
        return "SingInResult{" +
                "singInRetCode='" + singInRetCode + '\'' +
                ", singInRetMsg='" + singInRetMsg + '\'' +
                ", merNo='" + merNo + '\'' +
                ", termNo='" + termNo + '\'' +
                ", dekZek='" + dekZek + '\'' +
                ", zekKeyInd='" + zekKeyInd + '\'' +
                '}';
    }
}
