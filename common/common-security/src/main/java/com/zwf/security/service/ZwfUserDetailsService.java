package com.zwf.security.service;

import com.zwf.security.component.ZwfAuthenticationDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 描述: 获取UserDetails用Service，可以提供多个实例获取不同用户表的用户信息
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:12:31
 */
public interface ZwfUserDetailsService extends UserDetailsService {

    /**
     * 根据AuthenticationDetails判断此UserDetailsService是否适用
     * @param mksAuthenticationDetails
     * @return
     */
    boolean supports(ZwfAuthenticationDetails mksAuthenticationDetails);

    /**
     * 根据社交登录code 登录
     * @param code TYPE@CODE
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserBySocial(String code) throws UsernameNotFoundException;

    /**
     * 根据手机号获取用户信息
     * @param mobile
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;
}

