package com.yada.qrcode.payment.vspos.entity.proc;

import java.io.Serializable;
import java.util.Objects;

/**
 * 终端主键
 *
 * @author quhan
 */
public class TerminalPk implements Serializable {

    private static final long serialVersionUID = 3871659155629440057L;

    /**
     * 厂商代码
     */
    private String vendorCode;
    
    /**
     * SN信息
     */
    private String sn;

    public TerminalPk() {
    }

    public TerminalPk(String vendorCode, String sn) {
        this.vendorCode = vendorCode;
        this.sn = sn;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TerminalPk that = (TerminalPk) o;
        return Objects.equals(vendorCode, that.vendorCode) &&
                Objects.equals(sn, that.sn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode, sn);
    }

    @Override
    public String toString() {
        return "TerminalPk{" +
                "vendorCode='" + vendorCode + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}
