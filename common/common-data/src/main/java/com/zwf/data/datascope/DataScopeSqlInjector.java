package com.zwf.data.datascope;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * 描述: 支持自定义数据权限方法注入
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:17:57
 */
public class DataScopeSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectListByScope());
        methodList.add(new SelectPageByScope());
        methodList.add(new SelectCountByScope());
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }

}
