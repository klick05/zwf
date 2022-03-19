package com.zwf.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zwf.security.component.ZwfAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 13:35:46
 */
@JsonSerialize(using = ZwfAuth2ExceptionSerializer.class)
public class ServerErrorException extends ZwfAuth2Exception {

    public ServerErrorException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "server_error";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
