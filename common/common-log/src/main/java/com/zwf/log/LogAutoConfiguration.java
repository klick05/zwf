package com.zwf.log;

import com.zwf.core.util.KeyStrResolver;
import com.zwf.log.aspect.SysLogAspect;
import com.zwf.log.event.SysLogListener;
import com.zwf.upms.api.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 描述: 日志自动配置
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 23:26:53
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration {

    private final RemoteLogService remoteLogService;

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener(remoteLogService);
    }

    @Bean
    public SysLogAspect sysLogAspect(ApplicationEventPublisher publisher, KeyStrResolver resolver) {
        return new SysLogAspect(publisher, resolver);
    }

}
