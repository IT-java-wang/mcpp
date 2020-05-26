package com.yada.qrcode.payment.vspos.entity.send.manager.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * 下载TMK（收单主密钥）的响应消息体
 *
 * @author quhan
 */
public class DownloadTmkRespMsgBody implements RespMsgBody {

    @JSONField(name = "TmkDek")
    private String tmkDek;

    @JSONField(name = "TmkKcv")
    private String tmkKcv;

    public DownloadTmkRespMsgBody() {
    }

    public DownloadTmkRespMsgBody(String tmkDek, String tmkKcv) {
        this.tmkDek = tmkDek;
        this.tmkKcv = tmkKcv;
    }

    public String getTmkDek() {
        return tmkDek;
    }

    public void setTmkDek(String tmkDek) {
        this.tmkDek = tmkDek;
    }

    public String getTmkKcv() {
        return tmkKcv;
    }

    public void setTmkKcv(String tmkKcv) {
        this.tmkKcv = tmkKcv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DownloadTmkRespMsgBody that = (DownloadTmkRespMsgBody) o;
        return Objects.equals(tmkDek, that.tmkDek) &&
                Objects.equals(tmkKcv, that.tmkKcv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tmkDek, tmkKcv);
    }

    @Override
    public String toString() {
        return "DownloadTmkRespMsgBody{" +
                "tmkDek='" + tmkDek + '\'' +
                ", tmkKcv='" + tmkKcv + '\'' +
                '}';
    }
}
