package com.zwf.security.component;

import com.zwf.core.util.WebUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.provider.TokenRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * 描述: 异常格式化
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 15:36:59
 */
public class ZwfAuthenticationDetails implements Serializable {
    private static final long serialVersionUID = 550L;
    private String remoteAddress;
    private String sessionId;
    private String clientId;

    public ZwfAuthenticationDetails(HttpServletRequest request) {
        this.remoteAddress = request.getRemoteAddr();
        HttpSession session = request.getSession(false);
        this.sessionId = session != null ? session.getId() : null;
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        this.clientId = WebUtils.extractClientId(header).orElse("");
    }

    public ZwfAuthenticationDetails(TokenRequest request) {
        this.clientId = request.getClientId();
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getClientId() {
        return this.clientId;
    }
}
