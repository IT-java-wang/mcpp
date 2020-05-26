package com.yada.qrcode.payment.vspos.entity.send.manager.result;

import java.util.Objects;

/**
 * 下载TMK（收单主密钥）结果
 *
 * @author quhan
 */
public class DownloadTmkResult {

    /**
     * 响应码
     */
    private String retCode;

    /**
     * 响应信息
     */
    private String retMsg;

    /**
     * 受加密机保护的收单主密钥
     */
    private String dekTmk;


    public DownloadTmkResult() {
    }

    public DownloadTmkResult(String retCode, String retMsg, String dekTmk) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.dekTmk = dekTmk;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getDekTmk() {
        return dekTmk;
    }

    public void setDekTmk(String dekTmk) {
        this.dekTmk = dekTmk;
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
        DownloadTmkResult that = (DownloadTmkResult) o;
        return Objects.equals(retCode, that.retCode) &&
                Objects.equals(retMsg, that.retMsg) &&
                Objects.equals(dekTmk, that.dekTmk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retCode, retMsg, dekTmk);
    }

    @Override
    public String toString() {
        return "DownloadTmkResult{" +
                "retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", dekTmk='" + dekTmk + '\'' +
                '}';
    }
}
