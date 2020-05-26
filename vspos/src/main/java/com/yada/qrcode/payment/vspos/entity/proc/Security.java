package com.yada.qrcode.payment.vspos.entity.proc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * 存储ZMK
 *
 * @author quhan
 */
@Entity
@Table(name = "#{tableName.securityTableName}")
public class Security {

    @Id
    @Column(name = "ZMK")
    private String lmkZmk;

    public Security() {
    }

    public String getLmkZmk() {
        return lmkZmk;
    }

    public void setLmkZmk(String lmkZmk) {
        this.lmkZmk = lmkZmk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Security security = (Security) o;
        return Objects.equals(lmkZmk, security.lmkZmk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lmkZmk);
    }

    @Override
    public String toString() {
        return "Security{" +
                "lmkZmk='" + lmkZmk + '\'' +
                '}';
    }
}
