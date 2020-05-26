package com.yada.qrcode.payment.vspos;

import com.yada.qrcode.payment.encryption.IEncryption;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * 终端加解密密钥等信息
 *
 * @author quhan
 */
public class TerminalAuth {

    /**
     * 受加密机保护的通讯主密钥
     */
    private String lmkZek;

    /**
     * 受加密机保护的终端主密钥
     */
    private String lmkDek;

    /**
     * 受加密机保护的收单主密钥
     */
    private String lmkTmk;

    /**
     * 加密机实体
     */
    private IEncryption encryptionMachine;

    /**
     * Zek索引
     */
    private String zekKeyInd;


    public TerminalAuth() {
    }

    public TerminalAuth(IEncryption encryptionMachine) {
        this.encryptionMachine = encryptionMachine;
    }


    public String getLmkZek() {
        return lmkZek;
    }

    public void setLmkZek(String lmkZek) {
        this.lmkZek = lmkZek;
    }

    /**
     * 使用 受终端主密钥保护的通讯主密钥 获取 受加密机保护的通讯主密钥
     *
     * @param dekZek
     */
    public void setLmkZekByDekZek(String dekZek) {
        this.lmkZek = this.encryptionMachine.getLmkZekKeyArray(dekZek, this.lmkDek)[0];
    }

    public String getLmkDek() {
        return lmkDek;
    }

    public void setLmkDek(String lmkDek) {
        this.lmkDek = lmkDek;
    }

    /**
     * 获取受加密机保护的终端主密钥
     *
     * @param zmkDek 受加密机保护的终端主密钥
     */
    public void setLmkDekByZmkDek(String zmkDek) {
        this.lmkDek = this.encryptionMachine.getLmkDekByZmkDek(zmkDek);
    }

    public String getLmkTmk() {
        return lmkTmk;
    }

    public void setLmkTmk(String lmkTmk) {
        this.lmkTmk = lmkTmk;
    }

    /**
     * 使用 受终端主密钥保护的收单主密钥 获取 受加密机保护的收单主密钥
     *
     * @param dekTmk 受终端主密钥保护的收单主密钥
     */
    public void setLmkTmkByDekTmk(String dekTmk) {
        this.lmkTmk = this.encryptionMachine.getLmkTmkByDekTmk(dekTmk, this.lmkDek);
    }

    /**
     * 加密智能POS需要的加密信息
     *
     * @param source 需要加密的明文
     * @return 密文
     * @throws IOException 加密异常
     */
    public byte[] encoding(String source) throws IOException, DecoderException {
        byte[] data =stringToByte(source.getBytes());
        return this.encryptionMachine.getByteDataByEncryption(data, lmkZek);
    }

    /**
     * 解密智能POS响应消息
     *
     * @param cipherText 智能POS响应密文
     * @return 智能POS响应明文
     * @throws IOException 解密异常
     */
    public byte[] decoding(String cipherText) throws IOException, DecoderException {
        return this.encryptionMachine.getByteDataByDecryption(Hex.decodeHex(cipherText), lmkZek);
    }

    /**
     * 下载收单主密钥的时候需要的签名方法
     *
     * @param source 原数据
     * @return 加密后的数据
     * @throws Exception 加密异常
     */
    public String getSign(String source) throws Exception {
        byte[] md5s = MessageDigest.getInstance("MD5").digest(source.getBytes());
        byte[] data = this.encryptionMachine.getByteDataByEncryption(stringToByte(md5s), this.lmkZek);
        return Hex.encodeHexString(data);
    }

    public String getZekKeyInd() {
        return zekKeyInd;
    }

    public void setZekKeyInd(String zekKeyInd) {
        this.zekKeyInd = zekKeyInd;
    }


    /**
     * 待加密数据不满8的整数倍，填充至8的整数倍
     * @param data 待加密数据
     * @return 填充后的数据源
     */
    public static byte[] stringToByte(byte[] data) throws DecoderException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(data);
        byte[] array=data;
        int index = array.length % 8;
        if(index > 0) {
            index = 8 - index;
        }else {
            index = 8;
        }
        for (int i =0 ;i < index - 1; i++) {
            byteArrayOutputStream.write(0xff);
        }
        byteArrayOutputStream.write(index);
        return byteArrayOutputStream.toByteArray();
    }
}
