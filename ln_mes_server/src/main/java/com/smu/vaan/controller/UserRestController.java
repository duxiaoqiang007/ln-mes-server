package com.smu.vaan.controller;

import com.smu.vaan.exception.ApiException;
import com.smu.vaan.model.MenuFactory;
import com.smu.vaan.model.MenuItem;
import com.smu.vaan.model.security.AuthorityName;
import com.smu.vaan.model.security.User;
import com.smu.vaan.repository.UserRepository;
import com.smu.vaan.security.JwtTokenUtil;
import com.smu.vaan.security.JwtUser;
import com.smu.vaan.security.JwtUserFactory;
import com.smu.vaan.utils.AuthorityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "npl/api/v1/user", method = RequestMethod.GET)
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityUtils authorityUtils;

//    /**
//     * 获取用户列表，需根据权限检索不同的数据
//     * 1. ROLE_ADVANCE_USER: 可查看本经营单位的所有用户
//     * 2. ROLE_ADMIN: 可查看全部用户
//     * @return 用户列表
//     */
//    @PreAuthorize(value = "hasAnyRole('ROLE_ADVANCE_USER', 'ROLE_ADMIN')")
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public Page<User> getUsers(
//            UsernamePasswordAuthenticationToken p,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
//
//        JwtUser user = (JwtUser) p.getPrincipal();
//        Pageable pageable = new PageRequest(page, pageSize, null);
//        Page<User> userPage;
//        if (authorityUtils.hasRole(user, AuthorityName.ROLE_ADVANCE_USER)) {
//            userPage = userRepository.findByDepartmentId(user.getDepartment().getCode(), pageable);
//        } else if (authorityUtils.hasRole(user, AuthorityName.ROLE_ADMIN)) {
//            userPage = userRepository.findAll(pageable);
//        } else {
//            throw new AccessDeniedException("权限不足");
//        }
//        return userPage;
//    }
//
//    @PreAuthorize(value = "hasAnyRole('ROLE_ADVANCE_USER', 'ROLE_ADMIN')")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteUserById(
//            @PathVariable(value = "id") String id,
//            UsernamePasswordAuthenticationToken p) throws ApiException {
//        User userWillBeDelete = userRepository.findOne(id);
//        if (null == userWillBeDelete ) {
//            throw new UsernameNotFoundException("没有找到指定用户");
//        }
//        String companyCode = userWillBeDelete.getParentCompany().getCode();
//        JwtUser manager = authorityUtils.getUser(p);
//        // 不能删除自己
//        if (manager.getId().equals(userWillBeDelete.getId())) {
//            throw new ApiException(20001, "不能删除自己");
//        }
//        if (authorityUtils.hasRole(manager, AuthorityName.ROLE_ADMIN)) {
//            userRepository.delete(id);
//            return ResponseEntity.ok("{\"status\":\"成功\"}");
////            return ResponseEntity.ok("删除成功");
//        } else if (authorityUtils.hasRole(manager, AuthorityName.ROLE_ADVANCE_USER)) {
//            if (manager.getParentCompany().getCode().equals(companyCode)) {
//                userRepository.delete(id);
//
//                return ResponseEntity.ok("{\"status\":\"成功\"}");
//
//            } else {
//                throw new AccessDeniedException("权限不足");
//            }
//
//        } else {
//            throw new AccessDeniedException("权限不足");
//        }
//    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }


    // 根据用户权限返回菜单栏
    @RequestMapping(path = "/menu", method = RequestMethod.POST)
    public List<MenuItem> getMenuForUser(
            UsernamePasswordAuthenticationToken p
    ) {
        JwtUser user = authorityUtils.getUser(p);
        List<MenuItem> menu = new ArrayList<MenuItem>() {
        };
        if (authorityUtils.hasRole(user, AuthorityName.ROLE_ADMIN)) {
            return MenuFactory.getAdminMenu();
        }
        if (authorityUtils.hasRole(user, AuthorityName.ROLE_AGENT_USER)) {
            menu = addMenuItems(menu, MenuFactory.getMenuItemsByAuth(AuthorityName.ROLE_AGENT_USER));
        }
        if (authorityUtils.hasRole(user, AuthorityName.ROLE_STORAGE_USER)) {
            menu = addMenuItems(menu, MenuFactory.getMenuItemsByAuth(AuthorityName.ROLE_STORAGE_USER));
        }
        if (authorityUtils.hasRole(user, AuthorityName.ROLE_ADVANCE_USER)) {
            menu = addMenuItems(menu, MenuFactory.getMenuItemsByAuth(AuthorityName.ROLE_ADVANCE_USER));
        }
        if (authorityUtils.hasRole(user, AuthorityName.ROLE_AGENT_EMPLOYEE)) {
            menu = addMenuItems(menu, MenuFactory.getMenuItemsByAuth(AuthorityName.ROLE_AGENT_EMPLOYEE));
        }
        if (authorityUtils.hasRole(user, AuthorityName.ROLE_STORAGE_EMPLOYEE)) {
            menu = addMenuItems(menu, MenuFactory.getMenuItemsByAuth(AuthorityName.ROLE_STORAGE_EMPLOYEE));
        }
        if (authorityUtils.hasRole(user, AuthorityName.ROLE_AGENT_UPLOADER)) {
            menu = addMenuItems(menu, MenuFactory.getMenuItemsByAuth(AuthorityName.ROLE_AGENT_UPLOADER));
        }
        if (authorityUtils.hasRole(user, AuthorityName.ROLE_STORAGE_UPLOADER)) {
            menu = addMenuItems(menu, MenuFactory.getMenuItemsByAuth(AuthorityName.ROLE_STORAGE_UPLOADER));

        }

        return menu.stream().collect(Collectors.toList());
    }

    private List<MenuItem> addMenuItems(List<MenuItem> menu, List<MenuItem> menuItems) {
        for (MenuItem item: menuItems) {
            if(!menu.contains(item)) {
                menu.add(item);
            }
        }
        return menu;

    }





}
