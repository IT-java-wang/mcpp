package com.yada.qrcode.payment.vspos.entity.send.manager.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.yada.qrcode.payment.vspos.entity.send.base.MsgBody;

import java.util.Objects;

/**
 * 位置上送消息体
 *
 * @author quhan
 */
public class PositionUploadMsgBody implements MsgBody {


    /**
     * 位置信息 经度，纬度 用 ， 分割
     */
    @JSONField(name = "Position")
    private String position;

    /**
     * 省
     */
    @JSONField(name = "Position")
    private String province;

    /**
     * 市
     */
    @JSONField(name = "City")
    private String city;

    /**
     * 区
     */
    @JSONField(name = "District")
    private String district;

    /**
     * 镇
     */
    @JSONField(name = "Town")
    private String town;

    /**
     * 村
     */
    @JSONField(name = "Village")
    private String village;

    /**
     * 街道
     */
    @JSONField(name = "Street")
    private String street;

    /**
     * 门牌号
     */
    @JSONField(name = "StreetNo")
    private String streetNo;

    public PositionUploadMsgBody() {
    }

    public PositionUploadMsgBody(String position, String province, String city, String district, String town, String village, String street, String streetNo) {
        this.position = position;
        this.province = province;
        this.city = city;
        this.district = district;
        this.town = town;
        this.village = village;
        this.street = street;
        this.streetNo = streetNo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PositionUploadMsgBody that = (PositionUploadMsgBody) o;
        return Objects.equals(position, that.position) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(district, that.district) &&
                Objects.equals(town, that.town) &&
                Objects.equals(village, that.village) &&
                Objects.equals(street, that.street) &&
                Objects.equals(streetNo, that.streetNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, province, city, district, town, village, street, streetNo);
    }

    @Override
    public String toString() {
        return "PositionUploadMsgBody{" +
                "position='" + position + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", town='" + town + '\'' +
                ", village='" + village + '\'' +
                ", street='" + street + '\'' +
                ", streetNo='" + streetNo + '\'' +
                '}';
    }
}
