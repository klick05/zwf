package com.zwf.data.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * 描述: 租户工具类
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:30:12
 */
@UtilityClass
public class TenantContextHolder {

    private final ThreadLocal<Integer> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();

    /**
     * TTL 设置租户ID<br/>
     * <b>谨慎使用此方法,避免嵌套调用。尽量使用 {@code TenantBroker} </b>
     * @param tenantId
     * @see TenantBroker
     */
    public void setTenantId(Integer tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     * @return
     */
    public Integer getTenantId() {
        return THREAD_LOCAL_TENANT.get();
    }

    public void clear() {
        THREAD_LOCAL_TENANT.remove();
    }

}

