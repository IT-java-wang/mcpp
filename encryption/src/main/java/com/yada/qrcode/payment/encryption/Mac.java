/*
 * @author 作者: qugang
 * @E-mail 邮箱: qgass@163.com
 * @date 创建时间：2018/1/20
 * 类说明     计算mac 前8个字节与后8个字节做des 结果在与后8个字节做des 如果最后不足8个字节，补0,最后一次运算做3DES
 */
package com.yada.qrcode.payment.encryption;



import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Mac {
    /**
     * 计算mac
     *
     * @param byteArrayOutputStream 参与验签的消息
     * @param key     des key
     * @return 计算结果
     */
    public static String computer(ByteArrayOutputStream byteArrayOutputStream,
                                            String key) {
        try {

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            byte[] macKey = computerMac(byteArrayInputStream, key);
            byteArrayInputStream.close();
            byteArrayOutputStream.close();
            return  Hex.encodeHexString(macKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 计算mac
     */
    private static byte[] computerMac(ByteArrayInputStream byteArrayInputStream, String key) throws IOException {
        byte[] tempArray = new byte[8];
        int len = byteArrayInputStream.read(tempArray);
        if (len == 8) {
            return computerDes(tempArray, byteArrayInputStream, key);
        } else {
            return null;
        }
    }

    private static byte[] computerDes(byte[] head, ByteArrayInputStream byteArrayInputStream, String key) throws IOException {
        byte[] tempArray = new byte[8];
        int result = byteArrayInputStream.read(tempArray);
        if (result == -1) {
            return DesUtil.doubleKeyDes(key, head);
        } else {
            return computerDes(computerXOR(head, tempArray), byteArrayInputStream, key);
        }
    }

    private static byte[] computerXOR(byte[] byteA, byte[] byteB) {
        byte[] tempXorArray = new byte[8];
        for (int i = 0; i < 8; i++) {
            tempXorArray[i] = (byte) (byteA[i] ^ byteB[i]);
        }
        return tempXorArray;
    }
}
