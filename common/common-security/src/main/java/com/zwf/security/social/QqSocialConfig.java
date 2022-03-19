package com.zwf.security.social;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述: qq登录配置信息
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:57:01
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "social.qq")
public class QqSocialConfig {

    private String appid;

    private String secret;

}
