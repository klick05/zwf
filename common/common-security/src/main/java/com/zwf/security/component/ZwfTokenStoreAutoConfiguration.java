package com.zwf.security.component;

import cn.hutool.core.util.StrUtil;
import com.zwf.core.constant.SecurityConstants;
import com.zwf.core.util.KeyStrResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 描述: redis token store 自动配置
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 16:16:07
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class ZwfTokenStoreAutoConfiguration {
    private final KeyStrResolver resolver;

    private final RedisConnectionFactory connectionFactory;

    @Bean
    public TokenStore tokenStore() {
        ZwfRedisTokenStore tokenStore = new ZwfRedisTokenStore(connectionFactory, resolver);
        tokenStore.setPrefix(SecurityConstants.PIGX_PREFIX + SecurityConstants.OAUTH_PREFIX);
        tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
            @Override
            public String extractKey(OAuth2Authentication authentication) {
                // 增加租户隔离部分 租户ID:原生计算值
                return resolver.extract(super.extractKey(authentication), StrUtil.COLON);
            }
        });
        return tokenStore;
    }

}
