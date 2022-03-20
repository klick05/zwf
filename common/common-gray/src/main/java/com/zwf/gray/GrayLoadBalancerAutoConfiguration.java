package com.zwf.gray;

import com.zwf.gray.feign.GrayFeignRequestInterceptor;
import com.zwf.gray.rule.GrayLoadBalancerClientConfiguration;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/20 15:00:04
 */
@Configuration
@ConditionalOnProperty(value = "gray.rule.enabled", havingValue = "true")
@LoadBalancerClients(defaultConfiguration = GrayLoadBalancerClientConfiguration.class)
public class GrayLoadBalancerAutoConfiguration {

    @Bean
    public RequestInterceptor grayFeignRequestInterceptor() {
        return new GrayFeignRequestInterceptor();
    }

}
