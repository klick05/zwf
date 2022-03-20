package com.zwf.upms.api.feign;

import com.zwf.core.constant.SecurityConstants;
import com.zwf.core.constant.ServiceNameConstants;
import com.zwf.core.util.R;
import com.zwf.upms.api.entity.SysTenant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * 描述: 租户接口
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/20 15:23:15
 */
@FeignClient(contextId = "remoteTenantService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteTenantService {

    /**
     * 查询全部有效租户
     * @param from 内部标志
     * @return
     */
    @GetMapping("/tenant/list")
    R<List<SysTenant>> list(@RequestHeader(SecurityConstants.FROM) String from);

}

