package com.zwf.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zwf.security.component.ZwfAuth2ExceptionSerializer;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 13:34:38
 */
@JsonSerialize(using = ZwfAuth2ExceptionSerializer.class)
public class InvalidException extends ZwfAuth2Exception {

    public InvalidException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "invalid_exception";
    }

    @Override
    public int getHttpErrorCode() {
        return 426;
    }

}
