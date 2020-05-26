package com.yada.qrcode.payment.vspos.entity.proc;

import javax.persistence.*;
import java.util.Objects;

/**
 * 终端
 *
 * @author quhan
 */
@Entity
@IdClass(TerminalPk.class)
@Table(name = "#{tableName.terminalTableName}")
public class Terminal {


    /**
     * 厂商代码
     */
    @Id
    @Column(name = "VENDOR_CODE")
    private String vendorCode;

    /**
     * 终端SN
     */
    @Id
    @Column(name = "SN")
    private String sn;

    /**
     * 商户号
     */
    @Column(name = "MERCHANT_NO")
    private String merchantNo;

    /**
     * 母POS信息
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPER_POS_SN")
    private SuperPos superPos;

    /**
     * 终端号
     */
    @Column(name = "TERM_NO")
    private String termNo;

    /**
     * 受加密机保护的终端主密钥
     */
    @Column(name = "LMK_DEK")
    private String lmkDek;

    /**
     * 受区域主密钥保护的设备主密钥
     */
    @Column(name = "ZMK_DEK")
    private String zmkDek;

    /**
     * 受加密机保护的工作密钥
     */
    @Column(name = "LMK_ZEK")
    private String lmkZek;

    /**
     * 密钥索引
     */
    @Column(name = "ZEK_KEY_IND")
    private String zekKeyInd;

    /**
     * 受加密机保护的收单主密钥
     */
    @Column(name = "LMK_TMK")
    private String lmkTmk;

    /**
     * 需要重新签到
     */
    @Column(name = "NEED_SIGN_IN")
    private Boolean needSignIn;

    /**
     * 需要重新下载参数
     */
    @Column(name = "NEED_PARAM_DOWNLOAD")
    private Boolean needParamDownload;

    /**
     * 当前批次号
     */
    @Column(name = "BATCH_NO", length = 6)
    private String batchNo;

    /**
     * 位置信息ID
     */
    @OneToOne
    @JoinColumn(name = "POSITION_INFO_ID", referencedColumnName = "ID")
    private TerminalPositionInfo positionInfo;

    public Terminal() {
    }

    public Terminal(TerminalPk terminalPk) {
        this.vendorCode = terminalPk.getVendorCode();
        this.sn = terminalPk.getSn();
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

    public SuperPos getSuperPos() {
        return superPos;
    }

    public void setSuperPos(SuperPos superPos) {
        this.superPos = superPos;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public String getLmkDek() {
        return lmkDek;
    }

    public void setLmkDek(String lmkDek) {
        this.lmkDek = lmkDek;
    }

    public String getZmkDek() {
        return zmkDek;
    }

    public void setZmkDek(String zmkDek) {
        this.zmkDek = zmkDek;
    }

    public String getLmkZek() {
        return lmkZek;
    }

    public void setLmkZek(String lmkZek) {
        this.lmkZek = lmkZek;
    }

    public String getZekKeyInd() {
        return zekKeyInd;
    }

    public void setZekKeyInd(String zekKeyInd) {
        this.zekKeyInd = zekKeyInd;
    }

    public String getLmkTmk() {
        return lmkTmk;
    }

    public void setLmkTmk(String lmkTmk) {
        this.lmkTmk = lmkTmk;
    }

    public Boolean getNeedSignIn() {
        return needSignIn;
    }

    public void setNeedSignIn(Boolean needSignIn) {
        this.needSignIn = needSignIn;
    }

    public Boolean getNeedParamDownload() {
        return needParamDownload;
    }

    public void setNeedParamDownload(Boolean needParamDownload) {
        this.needParamDownload = needParamDownload;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public TerminalPositionInfo getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(TerminalPositionInfo positionInfo) {
        this.positionInfo = positionInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Terminal terminal = (Terminal) o;
        return Objects.equals(vendorCode, terminal.vendorCode) &&
                Objects.equals(sn, terminal.sn) &&
                Objects.equals(merchantNo, terminal.merchantNo) &&
                Objects.equals(superPos, terminal.superPos) &&
                Objects.equals(termNo, terminal.termNo) &&
                Objects.equals(lmkDek, terminal.lmkDek) &&
                Objects.equals(zmkDek, terminal.zmkDek) &&
                Objects.equals(lmkZek, terminal.lmkZek) &&
                Objects.equals(zekKeyInd, terminal.zekKeyInd) &&
                Objects.equals(lmkTmk, terminal.lmkTmk) &&
                Objects.equals(needSignIn, terminal.needSignIn) &&
                Objects.equals(needParamDownload, terminal.needParamDownload) &&
                Objects.equals(batchNo, terminal.batchNo) &&
                Objects.equals(positionInfo, terminal.positionInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode, sn, merchantNo, superPos, termNo, lmkDek, zmkDek, lmkZek, zekKeyInd, lmkTmk, needSignIn, needParamDownload, batchNo, positionInfo);
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "vendorCode='" + vendorCode + '\'' +
                ", sn='" + sn + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", superPos=" + superPos +
                ", termNo='" + termNo + '\'' +
                ", lmkDek='" + lmkDek + '\'' +
                ", zmkDek='" + zmkDek + '\'' +
                ", lmkZek='" + lmkZek + '\'' +
                ", zekKeyInd='" + zekKeyInd + '\'' +
                ", lmkTmk='" + lmkTmk + '\'' +
                ", needSignIn=" + needSignIn +
                ", needParamDownload=" + needParamDownload +
                ", batchNo='" + batchNo + '\'' +
                ", positionInfo=" + positionInfo +
                '}';
    }
}
