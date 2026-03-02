package com.lonbon.cloud.base.config;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.convert.Converter;
import org.noear.solon.core.exception.ConvertException;

import java.util.UUID;

//@Component
public class UUIDConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(String value) throws ConvertException {
        try {
            return UUID.fromString(value);
        }  catch (Exception ex) {
            throw new ConvertException(ex);
        }
    }
}
