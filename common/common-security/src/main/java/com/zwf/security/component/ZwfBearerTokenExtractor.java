package com.zwf.security.component;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 描述: 改造 {@link BearerTokenExtractor} 对公开权限的请求不进行校验
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 15:38:15
 */
@Component
@RequiredArgsConstructor
public class ZwfBearerTokenExtractor extends BearerTokenExtractor {

    private final PathMatcher pathMatcher = new AntPathMatcher();

    private final PermitAllUrlResolver permitAllUrlResolver;

    @Override
    public Authentication extract(HttpServletRequest request) {

        // 2. 判断请求方法是否匹配
        boolean result = permitAllUrlResolver.getIgnoreUrls().stream().anyMatch(url -> {
            List<String> strings = StrUtil.split(url, "|");
            // 1. 判断路径是否匹配
            boolean match = pathMatcher.match(strings.get(0), request.getRequestURI());
            // 2. 判断方法是否匹配
            if (strings.size() == 2) {
                List<String> methods = StrUtil.split(strings.get(1), StrUtil.COMMA);
                return CollUtil.contains(methods, request.getMethod()) && match;
            }
            return match;
        });
        return result ? null : super.extract(request);
    }

}

