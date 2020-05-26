package com.yada.qrcode.payment.vspos.entity.enums;

/**
 * @author quhan
 */

public enum SendResultEnum {

    /**
     * 完成
     */
    OK(1000, "成功"),

    /**
     * 处理失败
     */
    FAILED(2000, "失败"),

    /**
     * 网络错误
     */
    NET_ERROR(2001, "平台网络错误"),

    /**
     * 加密机错误
     */
    MACHINE_ERROR(2002, "加密机错误"),

    /**
     * 授权码无效
     */
    AUTHCODE_ERROR(2003,"授权码无效");

    private int code;
    private String msg;

    private SendResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
