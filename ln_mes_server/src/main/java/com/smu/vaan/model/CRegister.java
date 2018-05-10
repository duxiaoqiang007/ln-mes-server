package com.smu.vaan.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "C_REGISTER", schema = "LN_MES", catalog = "")
public class CRegister {
    private String register;
    private String company;
    private String ifAllow;

    @Basic
    @Id
    @Column(name = "REGISTER")
    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    @Basic
    @Column(name = "COMPANY")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "IF_ALLOW")
    public String getIfAllow() {
        return ifAllow;
    }

    public void setIfAllow(String ifAllow) {
        this.ifAllow = ifAllow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CRegister that = (CRegister) o;
        return Objects.equals(register, that.register) &&
                Objects.equals(company, that.company) &&
                Objects.equals(ifAllow, that.ifAllow);
    }

    @Override
    public int hashCode() {

        return Objects.hash(register, company, ifAllow);
    }
}
