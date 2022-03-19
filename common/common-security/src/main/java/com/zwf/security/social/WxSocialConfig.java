package com.zwf.security.social;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述: 微信登录配置
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:57:22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "social.wx")
public class WxSocialConfig {

    private String appid;

    private String secret;

}
