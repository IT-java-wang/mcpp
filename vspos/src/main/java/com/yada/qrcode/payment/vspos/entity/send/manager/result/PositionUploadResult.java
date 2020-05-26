package com.yada.qrcode.payment.vspos.entity.send.manager.result;

import java.util.Objects;

/**
 * 位置上传结果
 *
 * @author quhan
 */
public class PositionUploadResult {

    /**
     * 响应码
     */
    private String retCode;

    /**
     * 响应信息
     */
    private String retMsg;

    public PositionUploadResult() {
    }

    public PositionUploadResult(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PositionUploadResult that = (PositionUploadResult) o;
        return Objects.equals(retCode, that.retCode) &&
                Objects.equals(retMsg, that.retMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retCode, retMsg);
    }

    @Override
    public String toString() {
        return "PositionUploadResult{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                '}';
    }
}
