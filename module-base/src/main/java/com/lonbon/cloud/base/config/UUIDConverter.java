package com.lonbon.cloud.base.config;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.convert.Converter;
import org.noear.solon.core.exception.ConvertException;

import java.util.UUID;

/**
 * UUID 转换器，用于接收请求时，自动将字符串转换为 UUID 类型
 * 注意：
 * 1. 这里不能使用 @Component 注解自动注入，否则easy-query框架自动生成的实体类会缺少id字段的相关函数，原因未知，
 * 因此，需要手动注册到 Solon 容器中
 *      Solon.start(App.class, args, app->{
 *          app.converters().register(new UUIDConverter()); 
 *      });
 * 参靠：https://solon.noear.org/article/567  定制 Converter 处理特别类型转换
 */
@Component
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
