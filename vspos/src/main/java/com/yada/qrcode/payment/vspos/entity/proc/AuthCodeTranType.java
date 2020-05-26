package com.yada.qrcode.payment.vspos.entity.proc;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * 交易类型码对照
 *
 * @author quhan
 */
@Entity
@Table(name = "#{tableName.authCodeTranTypeTableName}")
public class AuthCodeTranType {
    /**
     * 授权码开头两位标记
     */
    @Id
    @Column(name = "CODE_MARK")
    private String codeMark;

    /**
     * 交易类型
     * WEIX：微信
     * ZFBA：支付宝
     * UPAY：银联
     */
    @Column(name = "TRAN_TYPE")
    private String tranType;

    public AuthCodeTranType() {
    }

    public AuthCodeTranType(String codeMark, String tranType) {
        this.codeMark = codeMark;
        this.tranType = tranType;
    }


    public String getCodeMark() {
        return codeMark;
    }

    public void setCodeMark(String codeMark) {
        this.codeMark = codeMark;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthCodeTranType that = (AuthCodeTranType) o;
        return Objects.equals(codeMark, that.codeMark) &&
                Objects.equals(tranType, that.tranType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeMark, tranType);
    }

    @Override
    public String toString() {
        return "AuthCodeTranType{" +
                "codeMark='" + codeMark + '\'' +
                ", tranType='" + tranType + '\'' +
                '}';
    }
}
