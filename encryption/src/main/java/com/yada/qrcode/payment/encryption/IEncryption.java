package com.yada.qrcode.payment.encryption;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface IEncryption {
    /***
     * 获取受本地主密钥（LMK）保护的终端主密钥（TMK）
     *
     * @param zmkTmk 受区域主密钥（LMK）保护的终端主密钥（TMK）
     * @return 受本地主密钥（LMK）保护的终端主密钥（TMK）
     */
    public String getLmkTmk(String zmkTmk);

    /***
     * 获取受本地主密钥（LMK）保护的终端认证密钥（TMK）
     *
     * @param lmkTmk 受本地主密钥（LMK）保护的终端主密钥（TMK）
     * @param tmkTak 受终端主密钥（TMK）保护的终端认证密钥（TAK）
     * @return 受本地主密钥（LMK）保护的终端认证密钥（TAK）
     */
    public String getLmkTak(String lmkTmk, String tmkTak);

    /***
     * 获取受本地主密钥（LMK）保护的终端PIN密钥（TPK）
     *
     * @param lmkTmk 受本地主密钥（LMK）保护的终端主密钥（TMK）
     * @param tmkTpk 受终端主密钥（TMK）保护的终端PIN密钥（TPK）
     * @return 受本地主密钥（LMK）保护的终端PIN密钥（TPK）
     */
    public String getLmkTpk(String lmkTmk, String tmkTpk);

    /***
     * 获取受终端主密钥（TPK）保护的PIN
     *
     * @param accountNo 账号
     * @param pin       PIN
     * @return 受终端主密钥（TPK）保护的PIN
     */
    public String getTpkPin(String accountNo, String pin, String lmkTpk);

    /***
     * 获取MAC
     *
     * @param macData mac验证的数据
     * @param lmkTak  受本地主密钥（LMK）保护的终端认证密钥（TAK）
     * @return MAC
     */
    public ByteBuffer getTakMac(ByteBuffer macData, String lmkTak);

    /***
     * 获取将ZPK保护的PIN密文转为受本地LMK密钥（LMK）保护的pin的密文
     *
     * @param hsmZpkLmk     受区域主密钥（LMK）保护的PIN主密钥（ZpkLmk）
     * @param classifiedPin 受本地PIN密钥（ZpkLmk）保护的pin的密文
     * @param cardNo        卡号/主账号
     * @return 受本地PIN密钥（ZpkLmk）保护的pin的明文
     * @author ZhangYaMin
     */
    public String getLmkPinFromZpkPin(String hsmZpkLmk, String classifiedPin, String cardNo);

    /***
     * 获取受主密钥（Lmk）保护的pin的明文
     *
     * @param classifiedPin 主密钥（Lmk）保护的pin的密文
     * @param cardNo        卡号/主账号
     * @return 受本地主密钥（Lmk）保护的pin的明文s
     * @author ZhangYaMin
     */
    public String DecryptPin(String classifiedPin, String cardNo);

    /***
     * 获取受主密钥(LMK)保护的PIN密钥对
     *
     * @param hsmKeyLmk 区域主密钥（LMK）
     * @return 返回PIN密钥对字符串
     * @author ZhangYaMin
     */
    public String GetLmkZpk(String hsmKeyLmk);

    /***
     * 获取受主密钥(LMK)保护的MAC密钥对
     *
     * @param hsmKeyLmk 区域主密钥（LMK）
     * @return 返回MAC密钥对字符串
     * @author ZhangYaMin
     */
    public String GetLmkZak(String hsmKeyLmk);

    /***
     * 获取受主密钥(LMK)保护的ZEK密钥对
     *
     * @param hsmKeyLmk 区域主密钥（LMK）
     * @return 返回ZEK密钥对字符串
     * @author ZhangYaMin
     */
    public String GetLmkZek(String hsmKeyLmk);

    /***
     * 通过Zak获取MAC
     *
     * @param lmkZak  受本地主密钥（LMK）保护的密钥（ZAK）
     * @param macData mac验证的数据
     * @return MAC
     * @author ZhangYaMin
     */
    public String getZakMac(String lmkZak, String macData);


    /***
     * 获取DEK密钥对
     * 指令 A0	模式1 密钥类型000 密钥方案LMK/ZMK  X
     *
     * @param lmkZmk 受本地主密钥（LMK）保护的区域密钥（ZMK）
     * @return [LmKDek, ZmKDek,DekKCV]
     * @author TX
     */
    public String[] getDekKeyArray(String lmkZmk);

    /***
     * 获取ZEK密钥对
     * 指令 FI	标志0  分隔符 ;
     *
     * @param lmkDek 受本地主密钥（LMK）保护的设备主密钥（DEK）
     * @return [DekZek, LmkZek, ZekKCV]
     * @author TX
     */
    public String[] getZekKeyArray(String lmkDek);

    /***
     * 获取TMK密钥对密钥对
     * 指令 A6 密钥类型 000 密钥方案LMK/ZMK  X
     *d
     * @param zmkTmk 受区域主密钥（ZMK）保护的终端主密钥（TMK）
     * @param lmkZmk 受本地主密钥（LMK）保护的区域主密钥（ZMK）
     * @return [lmkTmk, kcv]
     * @author TX
     */
    public String[] getTmkKeyArray(String zmkTmk, String lmkZmk);

    /***
     * 使用设备主密钥保护终端主秘钥
     * 指令 A8	密钥类型000 密钥方案LMK/ZMK  X
     *
     * @param lmkDek 受本地主密钥（LMK）保护的设备主密钥（DEK）
     * @param lmkTmk 受本地主密钥（LMK）保护的终端主密钥（TMK）
     * @return DekTmk
     * @author TX
     */
    public String getDekTmk(String lmkDek, String lmkTmk);

    /***
     * 解密通讯密钥加密的数据(二进制数据)
     * 指令 E0	加解密类型 1 密钥类型0 数据格式 1
     *
     * @param zekData 受通讯主密钥（ZEK）保护的数据
     * @param lmkZek  受本地主密钥（LMK）保护的终端主密钥（ZEK）
     * @return data
     * @author TX
     */
    public byte[] getByteDataByDecryption(byte[] zekData, String lmkZek) throws IOException;

    /***
     * 通过Zek保护数据(二进制数据)
     * 指令 E0	加解密类型 0 密钥类型0 数据格式 1
     *
     * @param data 数据
     * @param lmkZek 受本地主密钥（LMK）保护的通讯主密钥（ZEK）
     * @return 加密数据
     * @author TX
     */
    public byte[] getByteDataByEncryption(byte[] data, String lmkZek) throws IOException;

    /***
     * 获取TMK密钥对密钥对
     * 指令 A6 密钥类型 000 密钥方案LMK/ZMK  X
     *d
     * @param dekZek 受区域主密钥（ZMK）保护的终端主密钥（TMK）
     * @param lmkDek 受本地主密钥（LMK）保护的区域主密钥（ZMK）
     * @return [lmkZek, kcv]
     * @author qugang
     */
    String[] getLmkZekKeyArray(String dekZek, String lmkDek);


    /**
     * 获取LmkTmk
     *
     * @param dekTmk 受设备主秘钥保护的收单主秘钥
     * @param lmkDek 受加密机保护的设备主秘钥
     * @return 受加密机保护的收单主秘钥
     */
    String getLmkTmkByDekTmk(String dekTmk, String lmkDek);


    /**
     * 获取受加密机保护的终端主密钥
     *
     * @param zmkDek 受区域主密钥保护的终端主密钥
     * @return 受加密机主密钥保护的终端主密钥
     */
    String getLmkDekByZmkDek(String zmkDek);
}
