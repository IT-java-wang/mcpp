package com.yada.qrcode.payment.vspos.entity.proc;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * 母POS
 *
 * @author quhan
 */
@Entity
@Table(name = "#{tableName.superPosTableName}")
public class SuperPos {

    /**
     * SN号码
     */
    @Id
    @Column(name = "SN")
    private String sn;

    /**
     * 机构号
     */
    @Column(name = "ORG_NO")
    private String orgNo;

    /**
     * 厂商代码
     */
    @Column(name = "VENDOR_CODE")
    private String vendorCode;

    /**
     * 密钥文件路径
     */
    @Column(name = "PFX_FILE_PATH")
    private String pfxFilePath;

    /**
     * 密钥文件密码
     */
    @Column(name = "PFX_PASSWORD")
    private String pfxPassword;

    /**
     * 公钥，数据库存Base64格式
     */
    @Column(length = 4000, name = "PUBLIC_KEY")
    private String publicKey;

    /**
     * 私钥，数据库存Base64格式
     */
    @Column(length = 4000, name = "PRIVATE_KEY")
    private String privateKey;

    /**
     * 终端列表
     */
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "superPos", fetch = FetchType.LAZY)
    private List<Terminal> terminalList;


    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getPfxFilePath() {
        return pfxFilePath;
    }

    public void setPfxFilePath(String pfxFilePath) {
        this.pfxFilePath = pfxFilePath;
    }

    public String getPfxPassword() {
        return pfxPassword;
    }

    public void setPfxPassword(String pfxPassword) {
        this.pfxPassword = pfxPassword;
    }

    public List<Terminal> getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List<Terminal> terminalList) {
        this.terminalList = terminalList;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuperPos superPos = (SuperPos) o;
        return Objects.equals(sn, superPos.sn) &&
                Objects.equals(orgNo, superPos.orgNo) &&
                Objects.equals(vendorCode, superPos.vendorCode) &&
                Objects.equals(pfxFilePath, superPos.pfxFilePath) &&
                Objects.equals(pfxPassword, superPos.pfxPassword) &&
                Objects.equals(publicKey, superPos.publicKey) &&
                Objects.equals(privateKey, superPos.privateKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sn, orgNo, vendorCode, pfxFilePath, pfxPassword, publicKey, privateKey);
    }

    @Override
    public String toString() {
        return "SuperPos{" +
                "sn='" + sn + '\'' +
                ", orgNo='" + orgNo + '\'' +
                ", vendorCode='" + vendorCode + '\'' +
                ", pfxFilePath='" + pfxFilePath + '\'' +
                ", pfxPassword='" + pfxPassword + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }
}
