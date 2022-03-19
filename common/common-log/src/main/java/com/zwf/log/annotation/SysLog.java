package com.zwf.log.annotation;

import java.lang.annotation.*;

/**
 * 描述: 操作日志注解
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 23:06:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 描述
     * @return {String}
     */
    String value();

}
