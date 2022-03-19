package com.zwf.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zwf.security.component.ZwfAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 13:34:08
 */
@JsonSerialize(using = ZwfAuth2ExceptionSerializer.class)
public class ForbiddenException extends ZwfAuth2Exception {

    public ForbiddenException(String msg) {
        super(msg);
    }

    public ForbiddenException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "access_denied";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.FORBIDDEN.value();
    }

}
