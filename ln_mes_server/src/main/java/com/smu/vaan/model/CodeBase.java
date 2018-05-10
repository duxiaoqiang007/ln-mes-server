package com.smu.vaan.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vaan on 2017/2/22.
 */
@MappedSuperclass
public class CodeBase implements Serializable {
    @Id
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodeBase)) return false;
        CodeBase codeBase = (CodeBase) o;
        return Objects.equals(code, codeBase.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
