package com.zwf.data.datascope;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 描述: 数据权限查询参数
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:16:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {

    /**
     * 限制范围的字段名称
     */
    private String scopeName = "dept_id";

    /**
     * 具体的数据范围
     */
    private List<Integer> deptIds = new ArrayList<>();

    /**
     * 是否只查询本部门
     */
    private Boolean isOnly = false;

    /**
     * 函数名称，默认 SELECT * ;
     *
     * <ul>
     * <li>COUNT(1)</li>
     * </ul>
     */
    private DataScopeFuncEnum func = DataScopeFuncEnum.ALL;

}

