package com.yada.qrcode.payment.vspos;

/**
 * 虚拟智能POS接口
 *
 * @author quhan
 */
public interface IVirtualSmartPos<Traner> {

    /**
     * 创建交易
     *
     * @return 返回一个交易
     * @throws Exception 创建交易中报错，会抛出该异常信息
     */
    Traner createTraner() throws Exception;
}
