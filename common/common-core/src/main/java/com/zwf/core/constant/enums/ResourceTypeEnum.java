package com.zwf.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 资源类型
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 22:06:59
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

    /**
     * 图片资源
     */
    IMAGE("image", "图片资源"),

    /**
     * xml资源
     */
    XML("xml", "xml资源");

    /**
     * 类型
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;

}
