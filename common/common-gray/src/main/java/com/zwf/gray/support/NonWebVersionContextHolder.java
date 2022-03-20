package com.zwf.gray.support;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * 描述: 灰度版本号传递工具 ,在非web 调用feign 传递之前手动setVersion
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/20 14:59:34
 */
@UtilityClass
public class NonWebVersionContextHolder {

    private final ThreadLocal<String> THREAD_LOCAL_VERSION = new TransmittableThreadLocal<>();

    /**
     * TTL 设置版本号<br/>
     * @param version 版本号
     */
    public void setVersion(String version) {
        THREAD_LOCAL_VERSION.set(version);
    }

    /**
     * 获取TTL中的版本号
     * @return 版本 || null
     */
    public String getVersion() {
        return THREAD_LOCAL_VERSION.get();
    }

    public void clear() {
        THREAD_LOCAL_VERSION.remove();
    }

}

