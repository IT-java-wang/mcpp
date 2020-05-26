package com.yada.qrcode.payment.vspos.spos;

import com.yada.qrcode.payment.vspos.entity.proc.SuperPos;
import com.yada.qrcode.payment.vspos.entity.proc.Terminal;

/**
 * 有管理IST签到密钥的接口
 *
 * @author quhan
 */
public interface SmartPosStorage {

    /**
     * 用于存储LmkTmk
     *
     * @param vendorCode 厂商代码
     * @param tuSn       Sn号码
     * @param lmkTmk     需要存储 的LmkTmk
     */
    void saveLmkTmk(String vendorCode, String tuSn, String lmkTmk);

    /**
     * 用于存储lmkDek
     *
     * @param vendorCode 厂商代码
     * @param tuSn       Sn号码
     * @param lmkDek     需要存储的lmkDek
     */
    void saveLmkDek(String vendorCode, String tuSn, String lmkDek);


    /**
     * 用于存储Zek信息
     *
     * @param vendorCode 厂商代码
     * @param tuSn       Sn号码
     * @param zekInfo    需要存储的Zek信息
     */
    void saveLmkZek(String vendorCode, String tuSn, ZekInfo zekInfo);


    /**
     * 用于存储综合存储智能POS信息
     *
     * @param vendorCode   厂商代码
     * @param tuSn         SN号码
     * @param smartPosInfo 智能POS信息
     */
    void saveSmartPosInfo(String vendorCode, String tuSn, SmartPosInfo smartPosInfo);


    /**
     * 用于存储商户号、终端号、通讯主密钥、通讯密钥索引
     *
     * @param vendorCode 厂商代码
     * @param tuSn       SN号码
     * @param merchantNo 商户号
     * @param terminalNo 终端号
     * @param lmkZek     受加密机保护的通讯主密钥
     * @param zekKeyInd  通讯密钥索引
     */
    void saveMerchantNoTerminalNoAndZek(String vendorCode, String tuSn, String merchantNo, String terminalNo,
                                        String lmkZek, String zekKeyInd);

    /**
     * 获取LmkTmk
     *
     * @param vendorCode 厂商代码
     * @param tuSn       Sn号码
     * @return 设备对应的LmkTmk
     */
    String getLmkTmk(String vendorCode, String tuSn);

    /**
     * 获取LmkTpk
     *
     * @param vendorCode 厂商代码
     * @param tuSn       Sn号码
     * @return 设备的LmkTpk
     */
    String getLmkDek(String vendorCode, String tuSn);

    /**
     * 获取Zek信息
     *
     * @param vendorCode 厂商代码
     * @param tuSn       Sn号码
     * @return Zek信息，包括LmkZek和ZekKeyInd
     */
    ZekInfo getLmkZek(String vendorCode, String tuSn);

    /**
     * 获取设备的信息，包含设备商户号、终端号TMK、DEK、ZEK 信息
     *
     * @param vendorCode 厂商代码
     * @param tuSn       Sn号码
     * @return 设备的设备信息
     */
    SmartPosInfo getSmartPosInfo(String vendorCode, String tuSn);

    /**
     * 获取设备母POS信息
     *
     * @param vendorCode 厂商代码
     * @param tuSn       设备SN
     * @return 设备母POS信息
     */
    SuperPos getSuperPos(String vendorCode, String tuSn);

    /**
     * 检查终端是否需要重新签到
     *
     * @param vendorCode 厂商代码
     * @param tuSn       设备SN
     * @return 设备是否需要重新签到
     */
    boolean checkNeedSignIn(String vendorCode, String tuSn);

    /**
     * 检查终端是否需要重新下载参数
     *
     * @param vendorCode 厂商代码
     * @param tuSn       设备SN
     * @return 设备是否需要重新下载参数
     */
    boolean checkNeedParamDownload(String vendorCode, String tuSn);

    /**
     * 更新需要重新签到的状态
     *
     * @param vendorCode 厂商代码
     * @param tuSn       设备SN
     * @param needSignIn 是否需要重新签到
     */
    void saveNeedSignInStatus(String vendorCode, String tuSn, boolean needSignIn);

    /**
     * 更新是否需要重新下载参数的状态
     *
     * @param vendorCode        厂商代码
     * @param tuSn              设备SN
     * @param needParamDownload 是否需要重新下载参数
     */
    void saveNeedParamDownload(String vendorCode, String tuSn, boolean needParamDownload);

    /**
     * 更新是否需要上传位置信息的状态
     *
     * @param vendorCode 厂商代码
     * @param tuSn       设备SN
     * @param needUpload 是否需要上传
     */
    void saveNeedPositionInfoUpload(String vendorCode, String tuSn, boolean needUpload);

    /**
     * 获取所有终端信息
     *
     * @param vendorCode 厂商代码
     * @param tuSn       设备SN
     * @return 终端信息
     */
    Terminal getAllTerminalInfo(String vendorCode, String tuSn);

    /**
     * 获取批次号
     *
     * @param vendorCode 厂商代码
     * @param tuSn       设备SN
     * @return 当前设备批次号，如果为空，则设置为 000001
     */
    String getBatchNo(String vendorCode, String tuSn);

    /**
     * 更新批次号（获取下一个）
     *
     * @param vendorCode 厂商代码
     * @param tuSn       设备SN
     * @return 更新后的批次号
     */
    String updateBatchNo(String vendorCode, String tuSn);

    class ZekInfo {
        public String lmkZek;
        public String zekKeyInd;
    }

    class SmartPosInfo {
        public String merchantNo;
        public String terminalNo;
        public String lmkTmk;
        public String lmkDek;
        public ZekInfo zekInfo;
    }

}
