package com.zwf.security.annotation;

import java.lang.annotation.*;

/**
 * 描述: 服务调用鉴权注解
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 12:29:22
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

    /**
     * 是否AOP统一处理
     * @return false, true
     */
    boolean value() default true;

    /**
     * 需要特殊判空的字段(预留)
     * @return {}
     */
    String[] field() default {};

}
