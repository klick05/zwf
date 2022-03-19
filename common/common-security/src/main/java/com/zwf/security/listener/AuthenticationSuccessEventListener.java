package com.zwf.security.listener;

import cn.hutool.core.collection.CollUtil;
import com.zwf.security.handler.AuthenticationSuccessHandler;
import com.zwf.security.service.ZwfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述: 认证成功事件监听器
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:07:54
 */
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired(required = false)
    private AuthenticationSuccessHandler successHandler;

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = (Authentication) event.getSource();
        if (successHandler != null && isUserAuthentication(authentication)) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            HttpServletResponse response = requestAttributes.getResponse();

            successHandler.handle(authentication, request, response);
        }
    }

    private boolean isUserAuthentication(Authentication authentication) {
        return authentication.getPrincipal() instanceof ZwfUser
                || CollUtil.isNotEmpty(authentication.getAuthorities());
    }

}

