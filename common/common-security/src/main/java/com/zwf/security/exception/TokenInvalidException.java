package com.zwf.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zwf.security.component.ZwfAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 13:36:02
 */
@JsonSerialize(using = ZwfAuth2ExceptionSerializer.class)
public class TokenInvalidException extends ZwfAuth2Exception {

    public TokenInvalidException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "invalid_token";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.FAILED_DEPENDENCY.value();
    }

}
