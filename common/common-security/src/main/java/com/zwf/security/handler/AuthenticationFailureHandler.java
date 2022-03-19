package com.zwf.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述: 发放失败处理
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 13:37:04
 */
public interface AuthenticationFailureHandler {

    /**
     * 业务处理
     * @param authenticationException 错误信息
     * @param authentication 认证信息
     * @param request 请求信息
     * @param response 响应信息
     */
    void handle(AuthenticationException authenticationException, Authentication authentication,
                HttpServletRequest request, HttpServletResponse response);

}
