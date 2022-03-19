package com.zwf.security.annotation;

import com.zwf.security.component.ZwfResourceServerAutoConfiguration;
import com.zwf.security.component.ZwfSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * 描述: 资源服务注解
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 12:28:51
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ ZwfResourceServerAutoConfiguration.class, ZwfSecurityBeanDefinitionRegistrar.class })
public @interface EnableZwfResourceServer {

    /**
     * 是否开启本地模式
     * @return true
     */
    boolean isLocal() default true;

}
