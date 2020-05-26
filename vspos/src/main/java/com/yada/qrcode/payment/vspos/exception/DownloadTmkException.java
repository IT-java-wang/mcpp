package com.yada.qrcode.payment.vspos.exception;

/**
 * @author quhan
 */
public class DownloadTmkException extends Exception {

    /**
     * 签到的响应码
     */
    private String retCode;

    /**
     * 签到的响应信息
     */
    private String retMsg;

    public DownloadTmkException(String retCode, String retMsg) {
        super("下载收单主密钥出错：[" + retCode + "]" + retMsg);
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
}
