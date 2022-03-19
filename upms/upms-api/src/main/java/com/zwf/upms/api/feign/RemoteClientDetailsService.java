package com.zwf.upms.api.feign;

import com.zwf.core.constant.SecurityConstants;
import com.zwf.core.constant.ServiceNameConstants;
import com.zwf.core.util.R;
import com.zwf.upms.api.entity.SysOauthClientDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 16:59:46
 */
@FeignClient(contextId = "remoteClientDetailsService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteClientDetailsService {

    /**
     * 通过clientId 查询客户端信息
     * @param clientId 用户名
     * @param from 调用标志
     * @return R
     */
    @GetMapping("/client/getClientDetailsById/{clientId}")
    R<SysOauthClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId,
                                                  @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 查询全部客户端
     * @param from 调用标识
     * @return R
     */
    @GetMapping("/client/list")
    R<List<SysOauthClientDetails>> listClientDetails(@RequestHeader(SecurityConstants.FROM) String from);

}

