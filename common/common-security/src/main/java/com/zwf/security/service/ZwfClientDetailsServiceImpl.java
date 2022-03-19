package com.zwf.security.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zwf.core.constant.CacheConstants;
import com.zwf.core.constant.SecurityConstants;
import com.zwf.upms.api.entity.SysOauthClientDetails;
import com.zwf.upms.api.feign.RemoteClientDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 描述: 扩展 JdbcClientDetailsService 支持多租户
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 14:10:23
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class ZwfClientDetailsServiceImpl implements ClientDetailsService {

    private final RemoteClientDetailsService clientDetailsService;

    /**
     * 重写原生方法支持redis缓存
     * @param clientId
     * @return ClientDetails
     */
    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        SysOauthClientDetails clientDetails = clientDetailsService
                .getClientDetailsById(clientId, SecurityConstants.FROM_IN).getData();

        if (clientDetails == null) {
            return null;
        }
        // 适配成oauth2内置类型
        return clientDetailsWrapper(clientDetails);
    }

    /**
     * 客户端类型转化 参考
     * {@link org.springframework.security.oauth2.provider.client.JdbcClientDetailsService}
     * @param origin 数据库保存参数
     * @return target oauth 类型客户端参数
     */
    private ClientDetails clientDetailsWrapper(SysOauthClientDetails origin) {
        BaseClientDetails target = new BaseClientDetails();
        // 必选项
        target.setClientId(origin.getClientId());
        target.setClientSecret(String.format("{noop}%s", origin.getClientSecret()));

        if (ArrayUtil.isNotEmpty(origin.getAuthorizedGrantTypes())) {
            target.setAuthorizedGrantTypes(CollUtil.newArrayList(origin.getAuthorizedGrantTypes()));
        }

        if (StrUtil.isNotBlank(origin.getAuthorities())) {
            target.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(origin.getAuthorities()));
        }

        if (StrUtil.isNotBlank(origin.getResourceIds())) {
            target.setResourceIds(StringUtils.commaDelimitedListToSet(origin.getResourceIds()));
        }

        if (StrUtil.isNotBlank(origin.getWebServerRedirectUri())) {
            target.setRegisteredRedirectUri(StringUtils.commaDelimitedListToSet(origin.getWebServerRedirectUri()));
        }

        if (StrUtil.isNotBlank(origin.getScope())) {
            target.setScope(StringUtils.commaDelimitedListToSet(origin.getScope()));
        }

        if (StrUtil.isNotBlank(origin.getAutoapprove())) {
            target.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(origin.getAutoapprove()));
        }

        if (origin.getAccessTokenValidity() != null) {
            target.setAccessTokenValiditySeconds(origin.getAccessTokenValidity());
        }

        if (origin.getRefreshTokenValidity() != null) {
            target.setRefreshTokenValiditySeconds(origin.getRefreshTokenValidity());
        }

        String json = origin.getAdditionalInformation();
        if (StrUtil.isNotBlank(json)) {
            try {
                @SuppressWarnings("unchecked")
                Map<String, Object> additionalInformation = JSONUtil.toBean(json, Map.class);
                target.setAdditionalInformation(additionalInformation);
            }
            catch (Exception e) {
                log.warn("Could not decode JSON for additional information: " + json, e);
            }
        }

        return target;
    }

}

