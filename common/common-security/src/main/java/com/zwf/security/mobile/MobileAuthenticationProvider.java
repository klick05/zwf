package com.zwf.security.mobile;

import com.zwf.security.component.ZwfPreAuthenticationChecks;
import com.zwf.security.service.ZwfUserDetailsService;
import com.zwf.security.util.ZwfSecurityMessageSourceUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * 描述: 手机登录校验逻辑 验证码登录、社交登录
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:08:50
 */
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private MessageSourceAccessor messages = ZwfSecurityMessageSourceUtil.getAccessor();

    private UserDetailsChecker detailsChecker = new ZwfPreAuthenticationChecks();

    @Getter
    @Setter
    private ZwfUserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;

        String principal = mobileAuthenticationToken.getPrincipal().toString();
        UserDetails userDetails = userDetailsService.loadUserBySocial(principal);
        if (userDetails == null) {
            log.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.noopBindAccount", "Noop Bind Account"));
        }

        // 检查账号状态
        detailsChecker.check(userDetails);

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails,
                userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

}

