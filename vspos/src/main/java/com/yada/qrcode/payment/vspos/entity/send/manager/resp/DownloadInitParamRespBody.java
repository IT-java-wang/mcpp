package com.yada.qrcode.payment.vspos.entity.send.manager.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * @author quhan
 * 下载初始化参数需要的响应消息体
 */
public class DownloadInitParamRespBody implements RespMsgBody {

    /**
     * 设备主密钥
     */
    @JSONField(name = "DekZmk")
    private String dekZmk;

    /**
     * 设备主密钥校验值
     */
    @JSONField(name = "DekKcv")
    private String dekKcv;

    public DownloadInitParamRespBody() {
    }

    public DownloadInitParamRespBody(String dekZmk, String dekKcv) {
        this.dekZmk = dekZmk;
        this.dekKcv = dekKcv;
    }

    public String getDekZmk() {
        return dekZmk;
    }

    public void setDekZmk(String dekZmk) {
        this.dekZmk = dekZmk;
    }

    public String getDekKcv() {
        return dekKcv;
    }

    public void setDekKcv(String dekKcv) {
        this.dekKcv = dekKcv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DownloadInitParamRespBody that = (DownloadInitParamRespBody) o;
        return Objects.equals(dekZmk, that.dekZmk) &&
                Objects.equals(dekKcv, that.dekKcv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dekZmk, dekKcv);
    }

    @Override
    public String toString() {
        return "DownloadInitParamRespBody{" +
                "dekZmk='" + dekZmk + '\'' +
                ", dekKcv='" + dekKcv + '\'' +
                '}';
    }
}
