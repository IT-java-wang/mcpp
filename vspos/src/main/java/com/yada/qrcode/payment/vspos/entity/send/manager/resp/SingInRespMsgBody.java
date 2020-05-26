package com.yada.qrcode.payment.vspos.entity.send.manager.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * @author quhan
 */
public class SingInRespMsgBody implements RespMsgBody {

    @JSONField(name = "ZekDek")
    private String zekDek;

    @JSONField(name = "ZekKcv")
    private String zekKcv;

    @JSONField(name = "ZekKeyInd")
    private String zekKeyInd;

    @JSONField(name = "MerchantId")
    private String merchantId;

    @JSONField(name = "TerminalId")
    private String terminalId;

    public SingInRespMsgBody() {
    }

    public String getZekDek() {
        return zekDek;
    }

    public void setZekDek(String zekDek) {
        this.zekDek = zekDek;
    }

    public String getZekKcv() {
        return zekKcv;
    }

    public void setZekKcv(String zekKcv) {
        this.zekKcv = zekKcv;
    }

    public String getZekKeyInd() {
        return zekKeyInd;
    }

    public void setZekKeyInd(String zekKeyInd) {
        this.zekKeyInd = zekKeyInd;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SingInRespMsgBody that = (SingInRespMsgBody) o;
        return Objects.equals(zekDek, that.zekDek) &&
                Objects.equals(zekKcv, that.zekKcv) &&
                Objects.equals(zekKeyInd, that.zekKeyInd) &&
                Objects.equals(merchantId, that.merchantId) &&
                Objects.equals(terminalId, that.terminalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zekDek, zekKcv, zekKeyInd, merchantId, terminalId);
    }

    @Override
    public String toString() {
        return "SingInRespMsgBody{" +
                "zekDek='" + zekDek + '\'' +
                ", zekKcv='" + zekKcv + '\'' +
                ", zekKeyInd='" + zekKeyInd + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", terminalId='" + terminalId + '\'' +
                '}';
    }
}
