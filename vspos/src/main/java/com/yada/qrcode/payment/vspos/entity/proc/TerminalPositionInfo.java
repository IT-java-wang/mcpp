package com.yada.qrcode.payment.vspos.entity.proc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;

/**
 * 终端位置信息
 *
 * @author quhan
 */
@Entity
@Table(name = "#{tableName.terminalPositionInfoTableName}")
public class TerminalPositionInfo {

    /**
     * 主键
     */
    @Id
    @Column(name = "ID", length = 36)
    private String id;

    /**
     * 是否需要上传，为null或者true时需要上传，上传完成更新为false
     */
    @Column(name = "NEED_UPLOAD")
    private Boolean needUpload;

    /**
     * 位置信息 经度，纬度 用 ， 分割
     */
    @Column(name = "POSITION")
    private String position;

    /**
     * 省
     */
    @Column(name = "PROVINCE")
    private String province;

    /**
     * 市
     */
    @Column(name = "CITY")
    private String city;

    /**
     * 区
     */
    @Column(name = "DISTRICT")
    private String district;

    /**
     * 镇
     */
    @Column(name = "TOWN")
    private String town;

    /**
     * 村
     */
    @Column(name = "VILLAGE")
    private String village;

    /**
     * 街道
     */
    @Column(name = "STREET")
    private String street;

    /**
     * 门牌号
     */
    @Column(name = "STREET_NO")
    private String streetNo;

    /**
     * 厂商代码
     */
    @Column(name = "VENDOR_CODE")
    @NotBlank(message = "厂商代码不能为空")
    private String vendorCode;

    /**
     * 终端SN
     */
    @Column(name = "SN")
    @NotBlank(message = "SN号不能为空")
    private String sn;

    /**
     * 商户号
     */
    @Column(name = "MERCHANT_NO")
    private String merchantNo;

    /**
     * 终端号
     */
    @Column(name = "TERM_NO")
    private String termNo;


    public TerminalPositionInfo() {
        this.id = UUID.randomUUID().toString();
        this.needUpload = true;
    }

    public TerminalPositionInfo(String position, String province, String city, String district, String town,
                                String village, String street, String streetNo) {
        this.id = UUID.randomUUID().toString();
        this.needUpload = true;
        this.position = position;
        this.province = province;
        this.city = city;
        this.district = district;
        this.town = town;
        this.village = village;
        this.street = street;
        this.streetNo = streetNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getNeedUpload() {
        return needUpload;
    }

    public void setNeedUpload(Boolean needUpload) {
        this.needUpload = needUpload;
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

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TerminalPositionInfo that = (TerminalPositionInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(needUpload, that.needUpload) &&
                Objects.equals(position, that.position) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(district, that.district) &&
                Objects.equals(town, that.town) &&
                Objects.equals(village, that.village) &&
                Objects.equals(street, that.street) &&
                Objects.equals(streetNo, that.streetNo) &&
                Objects.equals(vendorCode, that.vendorCode) &&
                Objects.equals(sn, that.sn) &&
                Objects.equals(merchantNo, that.merchantNo) &&
                Objects.equals(termNo, that.termNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, needUpload, position, province, city, district, town, village, street, streetNo, vendorCode, sn, merchantNo, termNo);
    }

    @Override
    public String toString() {
        return "TerminalPositionInfo{" +
                "id='" + id + '\'' +
                ", needUpload=" + needUpload +
                ", position='" + position + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", town='" + town + '\'' +
                ", village='" + village + '\'' +
                ", street='" + street + '\'' +
                ", streetNo='" + streetNo + '\'' +
                ", vendorCode='" + vendorCode + '\'' +
                ", sn='" + sn + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", termNo='" + termNo + '\'' +
                '}';
    }
}
