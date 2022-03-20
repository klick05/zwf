package com.zwf.data.datascope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 数据权限类型
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:18:19
 */
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {

    /**
     * 查询全部数据
     */
    ALL(0, "全部"),

    /**
     * 自定义
     */
    CUSTOM(1, "自定义"),

    /**
     * 本级及子级
     */
    OWN_CHILD_LEVEL(2, "本级及子级"),

    /**
     * 本级
     */
    OWN_LEVEL(3, "本级");

    /**
     * 类型
     */
    private final int type;

    /**
     * 描述
     */
    private final String description;

}

