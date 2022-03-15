package com.zwf.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 流程状态
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 22:06:25
 */
@Getter
@AllArgsConstructor
public enum ProcessStatusEnum {

    /**
     * 激活
     */
    ACTIVE("active"),

    /**
     * 暂停
     */
    SUSPEND("suspend");

    /**
     * 状态
     */
    private final String status;

}
