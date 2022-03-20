package com.zwf.data.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;

/**
 * 描述: 租户信息拦截
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:27:44
 */
@Configuration
public class ZwfTenantConfiguration {

    @Bean
    public RequestInterceptor mksFeignTenantInterceptor() {
        return new ZwfFeignTenantInterceptor();
    }

    @Bean
    public ClientHttpRequestInterceptor mksTenantRequestInterceptor() {
        return new TenantRequestInterceptor();
    }

}
