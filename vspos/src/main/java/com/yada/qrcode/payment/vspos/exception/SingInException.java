package com.yada.qrcode.payment.vspos.exception;

/**
 * 签到异常
 *
 * @author quhan
 */
public class SingInException extends Exception {

    /**
     * 签到的响应码
     */
    private String retCode;

    /**
     * 签到的响应信息
     */
    private String retMsg;

    public SingInException(String retCode, String retMsg) {
        super("签到出错：[" + retCode + "]" + retMsg);
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
