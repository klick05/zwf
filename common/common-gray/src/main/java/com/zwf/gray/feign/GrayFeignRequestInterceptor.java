package com.zwf.gray.feign;

import cn.hutool.core.util.StrUtil;
import com.zwf.core.constant.CommonConstants;
import com.zwf.core.util.WebUtils;
import com.zwf.gray.support.NonWebVersionContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述: feign 请求VERSION 传递
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/20 14:56:36
 */
@Slf4j
public class GrayFeignRequestInterceptor implements RequestInterceptor {

    /**
     * Called for every request. Add data using methods on the supplied
     * {@link RequestTemplate}.
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        String reqVersion = WebUtils.getRequest() != null ? WebUtils.getRequest().getHeader(CommonConstants.VERSION)
                : NonWebVersionContextHolder.getVersion();

        if (StrUtil.isNotBlank(reqVersion)) {
            log.debug("feign gray add header version :{}", reqVersion);
            template.header(CommonConstants.VERSION, reqVersion);
        }
    }

}

