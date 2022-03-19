package com.zwf.security.component;

import com.zwf.core.constant.SecurityConstants;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: token增强，客户端模式不增强。
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 16:15:29
 */
public class ZwfTokenEnhancer implements TokenEnhancer {

    /**
     * Provides an opportunity for customization of an access token (e.g. through its
     * additional information map) during the process of creating a new token for use by a
     * client.
     * @param accessToken the current access token with its expiration and refresh token
     * @param authentication the current authentication including client and user details
     * @return a new token enhanced with additional information
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        final Map<String, Object> additionalInfo = new HashMap<>(8);
        String clientId = authentication.getOAuth2Request().getClientId();
        additionalInfo.put(SecurityConstants.CLIENT_ID, clientId);
        additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.PIGX_LICENSE);
        additionalInfo.put(SecurityConstants.ACTIVE, Boolean.TRUE);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }

}
