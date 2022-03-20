package com.zwf.data.resolver;

import com.zwf.core.util.KeyStrResolver;
import com.zwf.data.tenant.TenantContextHolder;

/**
 * 描述: 租户字符串处理（方便其他模块获取）
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:25:48
 */
public class TenantKeyStrResolver implements KeyStrResolver {

    /**
     * 传入字符串增加 租户编号:in
     * @param in 输入字符串
     * @param split 分割符
     * @return
     */
    @Override
    public String extract(String in, String split) {
        return TenantContextHolder.getTenantId() + split + in;
    }

    /**
     * 返回当前租户ID
     * @return
     */
    @Override
    public String key() {
        return String.valueOf(TenantContextHolder.getTenantId());
    }

}

