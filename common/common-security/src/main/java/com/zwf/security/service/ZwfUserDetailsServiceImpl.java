package com.zwf.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.zwf.core.constant.CacheConstants;
import com.zwf.core.constant.CommonConstants;
import com.zwf.core.constant.SecurityConstants;
import com.zwf.core.util.R;
import com.zwf.security.component.ZwfAuthenticationDetails;
import com.zwf.upms.api.dto.UserInfo;
import com.zwf.upms.api.entity.SysUser;
import com.zwf.upms.api.feign.RemoteUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述: 用户详细信息
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:13:08
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class ZwfUserDetailsServiceImpl implements ZwfUserDetailsService {

    private final RemoteUserService remoteUserService;

    private final CacheManager cacheManager;

    @Override
    public boolean supports(ZwfAuthenticationDetails mksAuthenticationDetails) {
        return true;
    }

    /**
     * 用户密码登录
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return cache.get(username, ZwfUser.class);
        }

        R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
    }

    /**
     * 根据社交登录code 登录
     * @param inStr TYPE@CODE
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserBySocial(String inStr) {
        return getUserDetails(remoteUserService.social(inStr, SecurityConstants.FROM_IN));
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        return getUserDetails(remoteUserService.infoByMobile(mobile, SecurityConstants.FROM_IN));
    }

    /**
     * 构建userdetails
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(R<UserInfo> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();
        boolean enabled = StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL);
        // 构造security用户

        return new ZwfUser(user.getUserId(), user.getDeptId(), user.getPhone(), user.getAvatar(), user.getTenantId(),
                user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled, true, true,
                !CommonConstants.STATUS_LOCK.equals(user.getLockFlag()), authorities);
    }

}
