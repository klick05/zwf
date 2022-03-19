package com.zwf.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zwf.core.constant.CommonConstants;
import com.zwf.security.exception.ZwfAuth2Exception;
import lombok.SneakyThrows;

/**
 * 描述:
 *
 * @author www.zhouwenfang.com
 * @version 1.0
 * @date 2022/03/19 15:35:59
 */
public class ZwfAuth2ExceptionSerializer extends StdSerializer<ZwfAuth2Exception> {

    public ZwfAuth2ExceptionSerializer() {
        super(ZwfAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(ZwfAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject();
        gen.writeObjectField("code", CommonConstants.FAIL);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getErrorCode());
        gen.writeEndObject();
    }

}
