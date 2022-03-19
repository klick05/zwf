package com.zwf.security.component;

import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 16:18:28
 */
public class ZwfWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, ZwfAuthenticationDetails> {

    public ZwfWebAuthenticationDetailsSource() {
    }

    public ZwfAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new ZwfAuthenticationDetails(context);
    }
}