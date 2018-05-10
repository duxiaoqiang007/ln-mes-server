package com.smu.vaan.model;

import com.smu.vaan.model.security.AuthorityName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaan on 2017/3/16.
 */

public class MenuFactory {

    
    public static List<MenuItem> getMenuItemsByAuth(AuthorityName authorityName) {
        List<MenuItem> list = new ArrayList<>();
        switch (authorityName) {
            case ROLE_AGENT_USER:
                list.add(new MenuItem("&#xE532;", "directions_boat", "进口代理", "import-agent"));
                return list;
            case ROLE_STORAGE_USER:
                list.add(new MenuItem("&#xE8D1;", "store", "仓储信息", "storage"));
            return list;
            case ROLE_ADVANCE_USER:
                list.add(new MenuItem("&#xE7FD;", "person", "用户管理", "user-management"));
                return list;
            case ROLE_AGENT_EMPLOYEE:
                list.add(new MenuItem("&#xE532;", "directions_boat", "进口代理", "import-agent"));
                return list;
            case ROLE_STORAGE_EMPLOYEE:
                list.add(new MenuItem("&#xE8D1;", "store", "仓储信息", "storage"));
                return list;
            case ROLE_AGENT_UPLOADER:
                list.add(new MenuItem("&#xE3B6;", "collections", "进口上传", "upload-image-import-agent"));
                return list;
            case ROLE_STORAGE_UPLOADER:
                list.add(new MenuItem("&#xE3F7;", "landscape", "仓储上传", "upload-image-storage"));
                return list;
            default:
                return list;
        }

    }

    public static List<MenuItem> getAdminMenu() {
        List<MenuItem> list = new ArrayList<>();
//        list.add(new MenuItem("&#xE88A;", "home", "主页", "home"));
        list.add(new MenuItem("&#xE532;", "directions_boat", "进口代理", "import-agent"));
        list.add(new MenuItem("&#xE8D1;", "store", "仓储信息", "storage"));
        list.add(new MenuItem("&#xE3B6;", "collections", "进口上传", "upload-image-import-agent"));
        list.add(new MenuItem("&#xE3F7;", "landscape", "仓储上传", "upload-image-storage"));
        list.add(new MenuItem("&#xE7FD;", "person", "用户管理", "user-management"));
//        list.add(new MenuItem("&#xE853;", "account_circle", "个人中心", "profile"));
        return list;
    }
}
