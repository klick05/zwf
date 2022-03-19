package com.zwf.security.component;

import com.zwf.core.constant.SecurityConstants;
import com.zwf.security.annotation.EnableZwfResourceServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 15:59:49
 */
@Slf4j
public class ZwfSecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 根据注解值动态注入资源服务器的相关属性
     * @param metadata 注解信息
     * @param registry 注册器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        if (registry.isBeanNameInUse(SecurityConstants.RESOURCE_SERVER_CONFIGURER)) {
            log.warn("本地存在资源服务器配置，覆盖默认配置:" + SecurityConstants.RESOURCE_SERVER_CONFIGURER);
            return;
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

        Boolean isLocal = (Boolean) metadata.getAnnotationAttributes(EnableZwfResourceServer.class.getName())
                .get("isLocal");
        if (isLocal) {
            beanDefinition.setBeanClass(ZwfLocalResourceServerConfigurerAdapter.class);
        }
        else {
            beanDefinition.setBeanClass(ZwfResourceServerConfigurerAdapter.class);
        }

        registry.registerBeanDefinition(SecurityConstants.RESOURCE_SERVER_CONFIGURER, beanDefinition);

    }

}