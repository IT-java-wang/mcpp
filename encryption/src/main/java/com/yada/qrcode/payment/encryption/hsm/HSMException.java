package com.yada.qrcode.payment.encryption.hsm;


/**
 * 加密机返回错误异常类
 * @author Tx
 */
public class HSMException extends RuntimeException {

    public HSMException(String message) {
        super(message);
    }
}