package com.zwf.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * 描述: 重写默认的 响应失败处理器，400 不作为异常
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 13:37:04
 */
public class RestResponseErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
			super.handleError(response);
		}
	}

}
