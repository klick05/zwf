package com.zwf.core.util;

/**
 * 描述: 字符串处理，方便其他模块解耦处理
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/15 22:14:06
 */
public interface KeyStrResolver {

    /**
     * 字符串加工
     * @param in 输入字符串
     * @param split 分割符
     * @return 输出字符串
     */
    String extract(String in, String split);

    /**
     * 字符串获取
     * @return 模块返回字符串
     */
    String key();

}
