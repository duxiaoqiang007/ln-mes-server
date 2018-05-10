package com.smu.vaan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Created by vaan on 2017/4/8.
 * 系统设置表
 */

@Entity
@Table(name = "sys_set")
public class SysSet {

    @Id
    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_value")
    private String itemValue;

    public String getItemName() {
        return itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SysSet)) return false;
        SysSet sysSet = (SysSet) o;
        return Objects.equals(itemName, sysSet.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }
}
