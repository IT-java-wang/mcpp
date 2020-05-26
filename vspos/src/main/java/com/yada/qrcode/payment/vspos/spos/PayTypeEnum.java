package com.yada.qrcode.payment.vspos.spos;

/**
 * 支付方式Enum
 *
 * @author quhan
 */
public enum PayTypeEnum {

    /**
     * 微信
     */
    WEIX("WEIX","201002"),

    /**
     * 支付宝
     */
    ZFBA("ZFBA","201001"),

    /**
     * 银联
     */
    UPAY("UPAY","201003"),

    /**
     * 电子支付
     */
    DZZF("DZZF","201012"),

    /**
     * 澳门支付
     */
    MPZF("MPZF","201015");


    private String value;

    private String tranId;

    PayTypeEnum(String value, String tranId) {
        this.value = value;
        this.tranId = tranId;
    }

    public String getValue() {
        return value;
    }

    public String getTranId() {
        return tranId;
    }
}
