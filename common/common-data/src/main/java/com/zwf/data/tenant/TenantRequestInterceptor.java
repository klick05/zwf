package com.zwf.data.tenant;

import com.zwf.core.constant.CommonConstants;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 描述: 传递 RestTemplate 请求的租户ID
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 18:30:52
 */
public class TenantRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        if (TenantContextHolder.getTenantId() != null) {
            request.getHeaders().set(CommonConstants.TENANT_ID, String.valueOf(TenantContextHolder.getTenantId()));
        }

        return execution.execute(request, body);
    }

}
