package com.yada.qrcode.payment.vspos.entity.send.tran.cipher;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * 交易请求信息，用于发送（密文）
 *
 * @author quhan
 */
public class TranReqPackMessage {

    /**
     * SPos接入版本号，Sn写1.0，TuSn写2.0
     */
    @JSONField(name = "SPOSVersion")
    private String sPosVersion;

    /**
     * 密钥索引
     */
    @JSONField(name = "ZekKeyInd")
    private String zekKeyInd;

    /**
     * 业务报文格式
     */
    @JSONField(name = "MsgType")
    private String msgType = "json";

    /**
     * 业务报文体
     */
    @JSONField(name = "MsgData")
    private String msgData;


    public String getsPosVersion() {
        return sPosVersion;
    }

    public void setsPosVersion(String sPosVersion) {
        this.sPosVersion = sPosVersion;
    }

    public String getZekKeyInd() {
        return zekKeyInd;
    }

    public void setZekKeyInd(String zekKeyInd) {
        this.zekKeyInd = zekKeyInd;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TranReqPackMessage that = (TranReqPackMessage) o;
        return Objects.equals(sPosVersion, that.sPosVersion) &&
                Objects.equals(zekKeyInd, that.zekKeyInd) &&
                Objects.equals(msgType, that.msgType) &&
                Objects.equals(msgData, that.msgData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sPosVersion, zekKeyInd, msgType, msgData);
    }

    @Override
    public String toString() {
        return "TranReqPackMessage{" +
                "sPosVersion='" + sPosVersion + '\'' +
                ", zekKeyInd='" + zekKeyInd + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgData='" + msgData + '\'' +
                '}';
    }
}
