package com.zwf.upms.api.dto;

import com.zwf.upms.api.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 17:06:47
 */
@Data
@ApiModel(value = "用户信息")
public class UserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    @ApiModelProperty(value = "用户基本信息")
    private SysUser sysUser;

    /**
     * 权限标识集合
     */
    @ApiModelProperty(value = "权限标识集合")
    private String[] permissions;

    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色标识集合")
    private Integer[] roles;

}

