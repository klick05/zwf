package com.zwf.security.util;

import cn.hutool.core.util.StrUtil;
import com.zwf.core.constant.SecurityConstants;
import com.zwf.security.service.ZwfUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述: 安全工具类
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:59:09
 */
@UtilityClass
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     * @param authentication
     * @return MksUser
     * <p>
     */
    public ZwfUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof ZwfUser) {
            return (ZwfUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public ZwfUser getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     * @return 角色集合
     */
    public List<Integer> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Integer> roleIds = new ArrayList<>();
        authorities.stream().filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
                .forEach(granted -> {
                    String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
                    roleIds.add(Integer.parseInt(id));
                });
        return roleIds;
    }

}

