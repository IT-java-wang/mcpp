package com.yada.qrcode.payment.vspos.entity.send.tran.plain.header;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgHeader;

import java.util.Objects;

/**
 * 交易请求报文头
 *
 * @author quhan
 */
public class TranMsgHeader implements MsgHeader {

    /**
     * 报文版本,初始版本号为1000
     */
    @JSONField(name = "MsgVer")
    private String msgVer;

    /**
     * 前端系统日期
     */
    @JSONField(name = "InDate")
    private String inDate;

    /**
     * 前端系统时间
     */
    @JSONField(name = "InTime")
    private String inTime;

    /**
     * 交易编码
     */
    @JSONField(name = "TranId")
    private String tranId;

    /**
     * 业务编号
     */
    @JSONField(name = "BussId")
    private String bussId;

    /**
     * 接入商户类型
     */
    @JSONField(name = "MerType")
    private String merType;

    /**
     * 报文方向
     */
    @JSONField(name = "Drctn")
    private String drctn = "11";

    /**
     * 敏感信息对称加密密钥
     */
    @JSONField(name = "EncKey")
    private String encKey;

    /**
     * 公钥ID
     */
    @JSONField(name = "PubKeyId")
    private String pubKeyId;

    /**
     * 前端系统标识
     */
    @JSONField(name = "SysNo")
    private String sysNo;

    /**
     * 通讯密钥索引编号
     */
    @JSONField(name = "ZekKeyInd")
    private String zekKeyInd;

    public TranMsgHeader() {
    }

    /**
     * @param msgVer    报文版本,初始版本号为1000
     * @param inDate    前端系统日期
     * @param inTime    前端系统时间
     * @param tranId    交易编码
     * @param bussId    业务编号 待确认
     * @param merType   接入商户类型，传01
     * @param encKey    敏感信息对称加密密钥 空
     * @param pubKeyId  公钥ID 空
     * @param sysNo     前端系统标识 待确认
     * @param zekKeyInd 密钥索引
     */
    public TranMsgHeader(String msgVer, String inDate, String inTime, String tranId, String bussId, String merType, String encKey, String pubKeyId, String sysNo, String zekKeyInd) {
        this.msgVer = msgVer;
        this.inDate = inDate;
        this.inTime = inTime;
        this.tranId = tranId;
        this.bussId = bussId;
        this.merType = merType;
        this.encKey = encKey;
        this.pubKeyId = pubKeyId;
        this.sysNo = sysNo;
        this.zekKeyInd = zekKeyInd;
    }

    public String getMsgVer() {
        return msgVer;
    }

    public void setMsgVer(String msgVer) {
        this.msgVer = msgVer;
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

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getBussId() {
        return bussId;
    }

    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public String getDrctn() {
        return drctn;
    }

    public void setDrctn(String drctn) {
        this.drctn = drctn;
    }

    public String getEncKey() {
        return encKey;
    }

    public void setEncKey(String encKey) {
        this.encKey = encKey;
    }

    public String getPubKeyId() {
        return pubKeyId;
    }

    public void setPubKeyId(String pubKeyId) {
        this.pubKeyId = pubKeyId;
    }

    public String getSysNo() {
        return sysNo;
    }

    public void setSysNo(String sysNo) {
        this.sysNo = sysNo;
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
        TranMsgHeader msgHeader = (TranMsgHeader) o;
        return Objects.equals(msgVer, msgHeader.msgVer) &&
                Objects.equals(inDate, msgHeader.inDate) &&
                Objects.equals(inTime, msgHeader.inTime) &&
                Objects.equals(tranId, msgHeader.tranId) &&
                Objects.equals(bussId, msgHeader.bussId) &&
                Objects.equals(merType, msgHeader.merType) &&
                Objects.equals(drctn, msgHeader.drctn) &&
                Objects.equals(encKey, msgHeader.encKey) &&
                Objects.equals(pubKeyId, msgHeader.pubKeyId) &&
                Objects.equals(sysNo, msgHeader.sysNo) &&
                Objects.equals(zekKeyInd, msgHeader.zekKeyInd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgVer, inDate, inTime, tranId, bussId, merType, drctn, encKey, pubKeyId, sysNo, zekKeyInd);
    }

    @Override
    public String toString() {
        return "TranMsgHeader{" +
                "msgVer='" + msgVer + '\'' +
                ", inDate='" + inDate + '\'' +
                ", inTime='" + inTime + '\'' +
                ", tranId='" + tranId + '\'' +
                ", bussId='" + bussId + '\'' +
                ", merType='" + merType + '\'' +
                ", drctn='" + drctn + '\'' +
                ", encKey='" + encKey + '\'' +
                ", pubKeyId='" + pubKeyId + '\'' +
                ", sysNo='" + sysNo + '\'' +
                ", zekKeyInd='" + zekKeyInd + '\'' +
                '}';
    }
}
