package com.zwf.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 前端类型类型
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 22:07:25
 */
@Getter
@AllArgsConstructor
public enum StyleTypeEnum {

    /**
     * 前端类型-avue 风格
     */
    AVUE("0", "avue 风格"),

    /**
     * 前端类型-element 风格
     */
    ELEMENT("1", "element 风格");

    /**
     * 类型
     */
    private String style;

    /**
     * 描述
     */
    private String description;

}
