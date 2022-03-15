package com.zwf.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 密码是否加密传输
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 22:05:03
 */
@Getter
@AllArgsConstructor
public enum EncFlagTypeEnum {

    /**
     * 是
     */
    YES("1", "是"),

    /**
     * 否
     */
    NO("0", "否");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

}
