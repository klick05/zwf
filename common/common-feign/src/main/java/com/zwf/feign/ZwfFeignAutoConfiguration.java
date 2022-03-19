package com.zwf.feign;

import com.zwf.feign.endpoint.FeignClientEndpoint;
import feign.Feign;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.ZwfFeignClientsRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述: feign 自动化配置
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 23:44:59
 */
@Configuration
@ConditionalOnClass(Feign.class)
@Import(ZwfFeignClientsRegistrar.class)
@AutoConfigureAfter(EnableFeignClients.class)
public class ZwfFeignAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnAvailableEndpoint
    public FeignClientEndpoint feignClientEndpoint(ApplicationContext context) {
        return new FeignClientEndpoint(context);
    }

}
