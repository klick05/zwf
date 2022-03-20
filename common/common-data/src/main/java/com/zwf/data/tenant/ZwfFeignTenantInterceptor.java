package com.zwf.data.tenant;

import com.zwf.core.constant.CommonConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:26:40
 */
@Slf4j
public class ZwfFeignTenantInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (TenantContextHolder.getTenantId() == null) {
            log.debug("TTL 中的 租户ID为空，feign拦截器 >> 跳过");
            return;
        }
        requestTemplate.header(CommonConstants.TENANT_ID, TenantContextHolder.getTenantId().toString());
    }

}

