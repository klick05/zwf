package com.zwf.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwf.core.constant.CommonConstants;
import com.zwf.core.util.R;
import com.zwf.security.util.ZwfSecurityMessageSourceUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 描述: 针对资源服务器的异常处理 {}不同细化异常处理
 * @link OAuth2AuthenticationProcessingFilter
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 15:39:05
 */
@Slf4j
@Component
@AllArgsConstructor
public class ZwfCommenceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        R<String> result = new R<>();
        result.setMsg(authException.getMessage());
        result.setData(authException.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        result.setCode(CommonConstants.FAIL);

        if (authException instanceof CredentialsExpiredException) {
            String msg = ZwfSecurityMessageSourceUtil.getAccessor().getMessage(
                    "AbstractUserDetailsAuthenticationProvider.credentialsExpired", authException.getMessage());
            result.setMsg(msg);
        }

        if (authException instanceof UsernameNotFoundException) {
            String msg = ZwfSecurityMessageSourceUtil.getAccessor().getMessage(
                    "AbstractUserDetailsAuthenticationProvider.noopBindAccount", authException.getMessage());
            result.setMsg(msg);
        }

        if (authException instanceof BadCredentialsException) {
            String msg = ZwfSecurityMessageSourceUtil.getAccessor().getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badClientCredentials", authException.getMessage());
            result.setMsg(msg);
        }

        if (authException instanceof InsufficientAuthenticationException) {
            String msg = ZwfSecurityMessageSourceUtil.getAccessor()
                    .getMessage("AbstractAccessDecisionManager.expireToken", authException.getMessage());
            response.setStatus(HttpStatus.FAILED_DEPENDENCY.value());
            result.setMsg(msg);
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }

}
