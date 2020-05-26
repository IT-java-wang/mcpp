/*
 * @author   作者: qugang
 * @E-mail   邮箱: qgass@163.com
 * @date     创建时间：2018/1/18
 * 类说明     Des 算法辅助类
 */
package com.yada.qrcode.payment.encryption;


import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesUtil {


    /**
     * des 算法加密
     *
     * @param data 数据
     * @param key  密钥
     * @return 加密的数据
     */
    public static byte[] encryptDES(byte[] data, byte[] key) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        DESKeySpec keySpec = new DESKeySpec(key);
        SecretKey secureKey = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secureKey);
        return cipher.doFinal(data);
    }


    /**
     * des 算法解密
     *
     * @param data 数据
     * @param key  密钥
     * @return 解密的数据
     */
    public static byte[] decryptDES(byte[] data, byte[] key)
            throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        DESKeySpec keySpec = new DESKeySpec(key);
        SecretKey secureKey = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secureKey);
        return cipher.doFinal(data);
    }

    /**
     * 双倍密钥 3次DES
     */
    public static byte[] doubleKeyDes(String keyString, byte[] src) {
        //使用密钥的前16字节，对数据DATA进行加密，得到加密的结果TMP1;
        try {
            byte[] temp1 = encryptDES(src, Hex.decodeHex(keyString.substring(0, 16).toCharArray()));
            //使用密钥的后16字节，对第一的计算结果TMP1，进行解密，得到解密的结果TMP2
            byte[] temp2 = decryptDES(temp1, Hex.decodeHex(keyString.substring(16, 32).toCharArray()));
            //再次使用密钥的前16字节，对第二次的计算结果TMP2，进行加密，得到加密的结果DEST。DEST就为最终的结果
            return encryptDES(temp2, Hex.decodeHex(keyString.substring(0, 16).toCharArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 双倍密钥 3次DES
     */
    public static byte[] doubleKeyEncryptDes(String keyString, byte[] src) {
        //使用密钥的前16字节，对数据DATA进行加密，得到加密的结果TMP1;
        try {
            byte[] temp1 = encryptDES(src, Hex.decodeHex(keyString.substring(0, 16).toCharArray()));
            //使用密钥的后16字节，对第一的计算结果TMP1，进行解密，得到解密的结果TMP2
            byte[] temp2 = decryptDES(temp1, Hex.decodeHex(keyString.substring(16, 32).toCharArray()));
            //再次使用密钥的前16字节，对第二次的计算结果TMP2，进行加密，得到加密的结果DEST。DEST就为最终的结果
            return encryptDES(temp2, Hex.decodeHex(keyString.substring(0, 16).toCharArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] doubleKeyDncryptDes(String keyString, byte[] src){
        try {
            byte[] temp1 = decryptDES(src, Hex.decodeHex(keyString.substring(0, 16).toCharArray()));
            //使用密钥的后16字节，对第一的计算结果TMP1，进行解密，得到解密的结果TMP2
            byte[] temp2 = encryptDES(temp1, Hex.decodeHex(keyString.substring(16, 32).toCharArray()));
            //再次使用密钥的前16字节，对第二次的计算结果TMP2，进行加密，得到加密的结果DEST。DEST就为最终的结果
            return decryptDES(temp2, Hex.decodeHex(keyString.substring(0, 16).toCharArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] threeEncryptDes(byte[] data,byte[] key) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        DESKeySpec keySpec = new DESKeySpec(key);
        SecretKey secureKey = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secureKey);
        return cipher.doFinal(data);
    }


    public static byte[] threeDecryptDES(byte[] data, byte[] key)
            throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        DESKeySpec keySpec = new DESKeySpec(key);
        SecretKey secureKey = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secureKey);
        return cipher.doFinal(data);
    }

}