package com.zwf.data.datascope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 数据权限函数类型
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:16:35
 */
@Getter
@AllArgsConstructor
public enum DataScopeFuncEnum {

    /**
     * 查询全部数据 SELECT * FROM (originSql) temp_data_scope WHERE temp_data_scope.dept_id IN
     * (1)
     */
    ALL("*", "全部"),

    /**
     * 查询函数COUNT SELECT COUNT(1) FROM (originSql) temp_data_scope WHERE
     * temp_data_scope.dept_id IN (1)
     */
    COUNT("COUNT(1)", "自定义");

    /**
     * 类型
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;

}

