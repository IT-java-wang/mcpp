package com.yada.qrcode.payment.vspos.entity.send.manager.result;

import java.util.Objects;

/**
 * @author quhan
 * 下载初始化参数需要的服务器返回结果信息
 */
public class DownloadInitParameterResult {

    /**
     * 响应码
     */
    private String retCode;

    /**
     * 响应信息
     */
    private String retMsg;

    /**
     * 设备主密钥
     */
    private String dekZmk;

    /**
     * 设备主密钥校验值
     */
    private String dekKcv;

    public DownloadInitParameterResult() {
    }

    public DownloadInitParameterResult(String retCode, String retMsg, String dekZmk, String dekKcv) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.dekZmk = dekZmk;
        this.dekKcv = dekKcv;
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

    public String getDekZmk() {
        return dekZmk;
    }

    public void setDekZmk(String dekZmk) {
        this.dekZmk = dekZmk;
    }

    public String getDekKcv() {
        return dekKcv;
    }

    public void setDekKcv(String dekKcv) {
        this.dekKcv = dekKcv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DownloadInitParameterResult that = (DownloadInitParameterResult) o;
        return Objects.equals(retCode, that.retCode) &&
                Objects.equals(retMsg, that.retMsg) &&
                Objects.equals(dekZmk, that.dekZmk) &&
                Objects.equals(dekKcv, that.dekKcv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retCode, retMsg, dekZmk, dekKcv);
    }

    @Override
    public String toString() {
        return "DownloadInitParameterResult{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", dekZmk='" + dekZmk + '\'' +
                ", dekKcv='" + dekKcv + '\'' +
                '}';
    }
}
