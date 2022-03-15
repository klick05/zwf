package com.zwf.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 22:04:41
 */
@Getter
@AllArgsConstructor
public enum DictTypeEnum {

    /**
     * 字典类型-系统内置（不可修改）
     */
    SYSTEM("1", "系统内置"),

    /**
     * 字典类型-业务类型
     */
    BIZ("0", "业务类");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

}
