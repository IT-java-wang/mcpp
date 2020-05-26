package com.yada.qrcode.payment.vspos;

import com.yada.qrcode.payment.encryption.hsm.EncryptionMachine;
import com.yada.qrcode.payment.vspos.spos.SmartPosStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 虚拟智能Pos创建工厂
 *
 * @author quhan
 */
@Repository
public class VirtualSmartPosFactory {

    /**
     * 智能Pos平台管理交易服务URL前缀
     */
    @Value("${spos.manageTranServerUrlPrefix}")
    private String manageTranServerUrlPrefix;

    /**
     * 智能Pos平台母Pos管理交易服务URL前缀
     */
    @Value("${spos.superPosServerUrlPrefix}")
    private String superPosServerUrlPrefix;

    /**
     * 智能Pos平台微信支付宝交易服务URL前缀
     */
    @Value("${spos.waTranServerUrlPrefix}")
    private String waTranServerUrlPrefix;

    /**
     * 智能Pos超时时间，实际交易的会将这个时间设置到连接超时时间以及读取超时时间，
     * 如果交易成功，可能时间会大于timeout时间，但是一定会小于timeout*2
     */
    @Value("${spos.timeout}")
    private String timeout;

    /**
     * 智能Pos加密机IP
     */
    @Value("${encryptionServerIp}")
    private String encryptionMachineIp;

    /**
     * 智能Pos加密机端口
     */
    @Value("${encryptionServerPort}")
    private String encryptionMachinePort;

    /**
     * 智能Pos业务编号
     */
    @Value("${spos.bussId}")
    private String bussId;

    /**
     * 前端系统标识
     */
    @Value("${spos.sysNo}")
    private String sysNo;

    @Autowired
    private SmartPosStorage smartPosStorage;

    /**
     * 虚拟智能Pos容器
     */
    private static Map<String, VirtualSmartPos> map = new HashMap<>();

    /**
     * 创建虚拟智能POS
     *
     * @param tuSn       SN
     * @param vendorCode 厂商代码
     * @param lmkZmk     Zmk信息
     * @return 虚拟智能POS
     * @throws Exception 创建异常将会报错
     */
    public VirtualSmartPos creatVirtualSmartPos(String tuSn, String vendorCode, String lmkZmk) throws Exception {
        String key = tuSn + vendorCode;
        VirtualSmartPos virtualSmartPos = map.get(key);

        if (virtualSmartPos == null) {
            synchronized (this) {
                virtualSmartPos = map.get(key);
                if (virtualSmartPos == null) {
                    int timeout = Integer.parseInt(this.timeout);
                    int encryptionMachinePort = Integer.parseInt(this.encryptionMachinePort);
                    virtualSmartPos = new VirtualSmartPos(tuSn, vendorCode, this.manageTranServerUrlPrefix, this.superPosServerUrlPrefix,
                            this.waTranServerUrlPrefix, timeout,
                            new EncryptionMachine(this.encryptionMachineIp, encryptionMachinePort, lmkZmk),
                            smartPosStorage, this.bussId, this.sysNo);
                    map.put(key, virtualSmartPos);
                }
            }
        }
        return virtualSmartPos;
    }


}
