package com.yada.qrcode.payment.vspos.entity.send.manager.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * @author quhan
 * 下载初始化参数需要的消息体
 */
public class InitReqMsgBody implements MsgBody {

    /**
     * 母POS 的 SN
     */
    @JSONField(name = "KeyDeviceSn")
    private String keyDeviceSn;

    /**
     * 时间戳
     */
    @JSONField(name = "Timestamp")
    private String timestamp;

    /**
     * 签名
     */
/*    @JSONField(name = "SignVlaue")
    private String signVlaue;*/
    public InitReqMsgBody() {
    }

    public InitReqMsgBody(String keyDeviceSn, String timestamp) {
        this.keyDeviceSn = keyDeviceSn;
        this.timestamp = timestamp;
    }

    public String getKeyDeviceSn() {
        return keyDeviceSn;
    }

    public void setKeyDeviceSn(String keyDeviceSn) {
        this.keyDeviceSn = keyDeviceSn;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

/*    public String getSignVlaue() {
        return signVlaue;
    }

    public void setSignVlaue(String signVlaue) {
        this.signVlaue = signVlaue;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InitReqMsgBody that = (InitReqMsgBody) o;
        return Objects.equals(keyDeviceSn, that.keyDeviceSn) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(keyDeviceSn, timestamp);
    }

    @Override
    public String toString() {
        return "InitReqMsgBody{" +
                "keyDeviceSn='" + keyDeviceSn + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
