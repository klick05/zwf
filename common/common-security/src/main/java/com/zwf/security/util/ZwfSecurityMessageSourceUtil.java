package com.zwf.security.util;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * 描述: 建议所有异常都使用此工具类型 避免无法复写 SpringSecurityMessageSource
 * @see org.springframework.security.core.SpringSecurityMessageSource 框架自身异常处理，
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:58:24
 */
public class ZwfSecurityMessageSourceUtil extends ReloadableResourceBundleMessageSource {

    public ZwfSecurityMessageSourceUtil() {
        setBasename("classpath:messages/messages");
        setDefaultLocale(Locale.CHINA);
    }

    public static MessageSourceAccessor getAccessor() {
        return new MessageSourceAccessor(new ZwfSecurityMessageSourceUtil());
    }

}
