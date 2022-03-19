package com.zwf.security.component;

import com.zwf.core.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 16:16:51
 */
@Slf4j
@EnableScheduling
@ConditionalOnBean(AuthorizationServerConfigurerAdapter.class)
public class ZwfTokenStoreCleanSchedule {

    @Scheduled(cron = "@hourly")
    public void doMaintenance() {
        ZwfRedisTokenStore tokenStore = SpringContextHolder.getBean(ZwfRedisTokenStore.class);
        long maintenance = tokenStore.doMaintenance();
        log.debug("清理Redis ZADD 过期 token 数量: {}", maintenance);
    }
}
