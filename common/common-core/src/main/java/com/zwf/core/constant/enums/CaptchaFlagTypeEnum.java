package com.zwf.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 验证码状态
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 22:04:03
 */
@Getter
@AllArgsConstructor
public enum CaptchaFlagTypeEnum {

    /**
     * 开启验证码
     */
    ON("1", "开启验证码"),

    /**
     * 关闭验证码
     */
    OFF("0", "关闭验证码");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

}
