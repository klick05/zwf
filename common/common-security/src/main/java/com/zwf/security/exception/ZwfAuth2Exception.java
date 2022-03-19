package com.zwf.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zwf.security.component.ZwfAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 13:35:16
 */
@JsonSerialize(using = ZwfAuth2ExceptionSerializer.class)
public class ZwfAuth2Exception extends OAuth2Exception {

    @Getter
    private String errorCode;

    public ZwfAuth2Exception(String msg) {
        super(msg);
    }

    public ZwfAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public ZwfAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

}

