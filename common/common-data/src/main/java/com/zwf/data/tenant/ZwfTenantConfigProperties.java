package com.zwf.data.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 多租户配置
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:27:08
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "zwf.tenant")
public class ZwfTenantConfigProperties {

    /**
     * 维护租户列名称
     */
    private String column = "tenant_id";

    /**
     * 多租户的数据表集合
     */
    private List<String> tables = new ArrayList<>();
}
