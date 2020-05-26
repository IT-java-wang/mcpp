package com.yada.qrcode.payment.vspos.entity.send.manager.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgHeader;

import java.util.Objects;

/**
 * 通用请求头
 *
 * @author quhan
 */
public class ManagerMessageGeneralHeader implements MsgHeader {

    /**
     * 交易码
     */
    @JSONField(name = "TranId")
    private String tranId;

    @JSONField(name = "InDate")
    private String inDate;

    @JSONField(name = "InTime")
    private String inTime;

    @JSONField(name = "ZekKeyInd")
    private String zekKeyInd;

    @JSONField(name = "SposVersion")
    private String sposVersion;

    @JSONField(name = "Sign")
    private String sign;

    public ManagerMessageGeneralHeader() {
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getZekKeyInd() {
        return zekKeyInd;
    }

    public void setZekKeyInd(String zekKeyInd) {
        this.zekKeyInd = zekKeyInd;
    }

    public String getSposVersion() {
        return sposVersion;
    }

    public void setSposVersion(String sposVersion) {
        this.sposVersion = sposVersion;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManagerMessageGeneralHeader that = (ManagerMessageGeneralHeader) o;
        return Objects.equals(tranId, that.tranId) &&
                Objects.equals(inDate, that.inDate) &&
                Objects.equals(inTime, that.inTime) &&
                Objects.equals(zekKeyInd, that.zekKeyInd) &&
                Objects.equals(sposVersion, that.sposVersion) &&
                Objects.equals(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tranId, inDate, inTime, zekKeyInd, sposVersion, sign);
    }

    @Override
    public String toString() {
        return "ManagerMessageGeneralHeader{" +
                "tranId='" + tranId + '\'' +
                ", inDate='" + inDate + '\'' +
                ", inTime='" + inTime + '\'' +
                ", zekKeyInd='" + zekKeyInd + '\'' +
                ", sposVersion='" + sposVersion + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
