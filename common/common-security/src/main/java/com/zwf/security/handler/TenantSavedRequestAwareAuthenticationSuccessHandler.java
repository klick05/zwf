package com.zwf.security.handler;

import com.zwf.core.util.KeyStrResolver;
import com.zwf.core.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述: 增强成功回调增加租户上下文避免极端情况下丢失问题
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 13:37:04
 */
@Slf4j
public class TenantSavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	protected final Log logger = LogFactory.getLog(this.getClass());

	private RequestCache requestCache = new HttpSessionRequestCache();

	public TenantSavedRequestAwareAuthenticationSuccessHandler() {
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		SavedRequest savedRequest = this.requestCache.getRequest(request, response);
		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);
		}

		if (isAlwaysUseDefaultTargetUrl()) {
			this.requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);
		}
		else {
			this.clearAuthenticationAttributes(request);
			// 增加租户信息
			assert savedRequest != null;
			String targetUrl = savedRequest.getRedirectUrl() + "&TENANT-ID="
					+ SpringContextHolder.getBean(KeyStrResolver.class).key();

			this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}
	}

}