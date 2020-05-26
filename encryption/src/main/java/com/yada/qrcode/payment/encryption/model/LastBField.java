package com.yada.qrcode.payment.encryption.model;

import com.payneteasy.tlv.HexUtil;

import java.nio.ByteBuffer;

/**
 * 报文最后的非固定长B类型域
 * Created by fengming on 3/31/17.
 */
public class LastBField implements IHsmField {

    private byte[] data = null;

    LastBField() {

    }

    LastBField(byte[] data) {
        this.data = data;
    }

    @Override
    public int length() {
        if (data == null) {
            throw new RuntimeException("must exec setValue before exec length");
        }
        return data.length;
    }

    @Override
    public String sValue() {
        if (data == null) {
            throw new RuntimeException("must exec setValue before exec sValue");
        }
        return HexUtil.toHexString(this.data);
    }

    @Override
    public byte[] value() {
        if (data == null) {
            throw new RuntimeException("must exec setValue before exec value");
        }
        return this.data;
    }

    @Override
    public byte[] fullValue() {
        return value();
    }

    @Override
    public int fullLength() {
        return length();
    }

    @Override
    public String type() {
        return "LastB";
    }

    @Override
    public void setValue(ByteBuffer data) {
        int length = data.remaining();
        this.data = new byte[length];
        data.get(this.data);
    }
}
