package com.zwf.upms.api.feign;

import com.zwf.core.constant.SecurityConstants;
import com.zwf.core.constant.ServiceNameConstants;
import com.zwf.core.util.R;
import com.zwf.upms.api.dto.SysLogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 23:25:45
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteLogService {

    /**
     * 保存日志
     * @param sysLog 日志实体
     * @param from 是否内部调用
     * @return succes、false
     */
    @PostMapping("/log/save")
    R<Boolean> saveLog(@RequestBody SysLogDTO sysLog, @RequestHeader(SecurityConstants.FROM) String from);

}
