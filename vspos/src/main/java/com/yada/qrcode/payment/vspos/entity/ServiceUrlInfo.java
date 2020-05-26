package com.yada.qrcode.payment.vspos.entity;

/**
 * 智能POS交易相关URl
 *
 * @author quhan
 */
public class ServiceUrlInfo {

    /**
     * 厂商代码
     */
    private String vendorCode;

    /**
     * 母POS管理URL
     */
    private String tuSn;

    /**
     * 管理端接口前缀
     */
    private String manageTranServerUrlPrefix;

    /**
     * 母POS管理服务前缀
     */
    private String superPosServerUrlPrefix;

    /**
     * 微信支付宝交易服务前缀
     */
    private String waTranServerUrlPrefix;

    public ServiceUrlInfo() {
    }

    public ServiceUrlInfo(String vendorCode, String tuSn) {
        this.vendorCode = vendorCode;
        this.tuSn = tuSn;
    }

    public ServiceUrlInfo(String manageTranServerUrlPrefix, String superPosServerUrlPrefix, String waTranServerUrlPrefix) {
        this.manageTranServerUrlPrefix = manageTranServerUrlPrefix;
        this.superPosServerUrlPrefix = superPosServerUrlPrefix;
        this.waTranServerUrlPrefix = waTranServerUrlPrefix;
    }

    public ServiceUrlInfo(String vendorCode, String tuSn, String manageTranServerUrlPrefix, String superPosServerUrlPrefix, String waTranServerUrlPrefix) {
        this.vendorCode = vendorCode;
        this.tuSn = tuSn;
        this.manageTranServerUrlPrefix = manageTranServerUrlPrefix;
        this.superPosServerUrlPrefix = superPosServerUrlPrefix;
        this.waTranServerUrlPrefix = waTranServerUrlPrefix;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getTuSn() {
        return tuSn;
    }

    public void setTuSn(String tuSn) {
        this.tuSn = tuSn;
    }


    /**
     * 获取本POS的签到地址
     *
     * @return 签到地址
     */
    public String getSingInTranServerUrl() {
        String singInTranServerUrl = this.manageTranServerUrlPrefix;

        if (!singInTranServerUrl.endsWith("/")) {
            singInTranServerUrl = singInTranServerUrl + "/";
        }

        singInTranServerUrl = singInTranServerUrl + "v1/firms/" + this.vendorCode + "/devices/" + this.tuSn + "/sign";

        return singInTranServerUrl;
    }


    /**
     * 获取下载收单主密钥接口的地址
     *
     * @return 下载收单主密钥接口的地址
     */
    public String getDownloadTmkServerUrl() {
        String downloadTmkServerUrl = this.manageTranServerUrlPrefix;

        if (!downloadTmkServerUrl.endsWith("/")) {
            downloadTmkServerUrl = downloadTmkServerUrl + "/";
        }

        downloadTmkServerUrl = downloadTmkServerUrl + "v1/firms/" + this.vendorCode + "/devices/" + this.tuSn + "/tmks/tmk";

        return downloadTmkServerUrl;
    }


    /**
     * 获取下载初始化参数(DEK)的服务地址
     *
     * @return 下载DEK的服务地址
     */
    public String getDownloadInitParameterServerUrl() {

        String downloadInitParameterServerUrl = this.superPosServerUrlPrefix;

        if (!downloadInitParameterServerUrl.endsWith("/")) {
            downloadInitParameterServerUrl = downloadInitParameterServerUrl + "/";
        }

        downloadInitParameterServerUrl = downloadInitParameterServerUrl + "v1/firms/" + this.vendorCode + "/devices/" +
                this.tuSn + "/initializations/initialization";

        return downloadInitParameterServerUrl;
    }

    /**
     * 获取智能POS交易服务器地址
     *
     * @return 智能POS交易服务器地址
     */
    public String getWaTranServerUrl() {
        String waTranServerUrl = this.waTranServerUrlPrefix;

        if (!waTranServerUrl.endsWith("/")) {
            waTranServerUrl = waTranServerUrl + "/";
        }

        waTranServerUrl = waTranServerUrl + "v1/firms/" + this.vendorCode + "/devices/" + this.tuSn;

        return waTranServerUrl;
    }

    /**
     * 获取初始化参数上传服务器地址
     *
     * @return 初始化参数上传服务器地址
     */
    public String getInitPositionUploadServerUrl() {
        String initPositionUploadServerUrl = this.manageTranServerUrlPrefix;

        if (!initPositionUploadServerUrl.endsWith("/")) {
            initPositionUploadServerUrl = initPositionUploadServerUrl + "/";
        }

        initPositionUploadServerUrl = initPositionUploadServerUrl + "v1/firms/" + this.vendorCode + "/devices/" +
                this.tuSn + "/initposition";

        return initPositionUploadServerUrl;
    }


    @Override
    public String toString() {
        return "ServiceUrlInfo{" +
                "firmCode='" + vendorCode + '\'' +
                ", tuSn='" + tuSn + '\'' +
                '}';
    }
}
