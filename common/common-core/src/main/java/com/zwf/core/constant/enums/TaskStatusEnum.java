package com.zwf.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 流程状态
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 22:07:54
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

    /**
     * 未提交
     */
    UNSUBMIT("0", "未提交"),

    /**
     * 审核中
     */
    CHECK("1", "审核中"),

    /**
     * 已完成
     */
    COMPLETED("2", "已完成"),

    /**
     * 驳回
     */
    OVERRULE("9", "驳回");

    /**
     * 类型
     */
    private final String status;

    /**
     * 描述
     */
    private final String description;

}
