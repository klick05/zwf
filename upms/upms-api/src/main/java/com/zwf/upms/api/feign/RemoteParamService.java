package com.zwf.upms.api.feign;

import com.zwf.core.constant.SecurityConstants;
import com.zwf.core.constant.ServiceNameConstants;
import com.zwf.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 描述: 查询参数相关
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/20 14:38:34
 */
@FeignClient(contextId = "remoteParamService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteParamService {

    /**
     * 通过key 查询参数配置
     * @param key key
     * @param from 声明成内部调用，避免MQ 等无法调用
     * @return
     */
    @GetMapping("/param/publicValue/{key}")
    R<String> getByKey(@PathVariable("key") String key, @RequestHeader(SecurityConstants.FROM) String from);

}

