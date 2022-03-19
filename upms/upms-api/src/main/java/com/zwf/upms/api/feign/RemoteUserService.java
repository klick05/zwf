package com.zwf.upms.api.feign;

import com.zwf.core.constant.SecurityConstants;
import com.zwf.core.constant.ServiceNameConstants;
import com.zwf.core.util.R;
import com.zwf.upms.api.dto.UserInfo;
import com.zwf.upms.api.entity.SysUser;
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
 * @date 2022/03/19 17:04:34
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户、角色信息
     * @param username 用户名
     * @param from 调用标志
     * @return R
     */
    @GetMapping("/user/info/{username}")
    R<UserInfo> info(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 通过用户名查询用户、角色信息
     * @param mobile 用户手机号
     * @param from 调用标志
     * @return R
     */
    @GetMapping("/mobile/info/{mobile}")
    R<UserInfo> infoByMobile(@PathVariable("mobile") String mobile, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 通过社交账号或手机号查询用户、角色信息
     * @param inStr appid@code
     * @param from 调用标志
     * @return
     */
    @GetMapping("/social/info/{inStr}")
    R<UserInfo> social(@PathVariable("inStr") String inStr, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 查询上级部门的用户信息
     * @param username 用户名
     * @return R
     */
    @GetMapping("/user/ancestor/{username}")
    R<List<SysUser>> ancestorUsers(@PathVariable("username") String username);

}

