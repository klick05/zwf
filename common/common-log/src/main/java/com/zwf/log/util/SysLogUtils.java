package com.zwf.log.util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.zwf.upms.api.dto.SysLogDTO;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 23:22:53
 */
@UtilityClass
public class SysLogUtils {

    public SysLogDTO getSysLog() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SysLogDTO sysLog = new SysLogDTO();
        sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
        sysLog.setType(LogTypeEnum.NORMAL.getType());
        sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setMethod(request.getMethod());
        sysLog.setUserAgent(request.getHeader("user-agent"));
        sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
        sysLog.setServiceId(getClientId());
        return sysLog;
    }

    public SysLogDTO getSysLog(HttpServletRequest request, String username) {
        SysLogDTO sysLog = new SysLogDTO();
        sysLog.setCreateBy(username);
        sysLog.setType(LogTypeEnum.NORMAL.getType());
        sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setMethod(request.getMethod());
        sysLog.setUserAgent(request.getHeader("user-agent"));
        sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
        sysLog.setServiceId(getClientId());
        return sysLog;
    }

    /**
     * 获取客户端
     * @return clientId
     */
    private String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }

    /**
     * 获取用户名称
     * @return username
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

}
