package com.smu.vaan.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vaan on 2017/3/16.
 */
public class MenuItem implements Serializable{
    private String icon;
    private String iconName;
    private String name;
    private String routerLink;

    public MenuItem(String icon, String iconName, String name, String routerLink) {
        this.icon = icon;
        this.iconName = iconName;
        this.name = name;
        this.routerLink = routerLink;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(icon, menuItem.icon) &&
                Objects.equals(iconName, menuItem.iconName) &&
                Objects.equals(name, menuItem.name) &&
                Objects.equals(routerLink, menuItem.routerLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icon, iconName, name, routerLink);
    }
}
