package com.zwf.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 描述: Mybatis 配置
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:15:17
 */
@Data
@RefreshScope
@ConfigurationProperties("zwf.mybatis")
public class ZwfMybatisProperties {

    /**
     * 是否打印可执行 sql
     */
    private boolean showSql = true;

}
