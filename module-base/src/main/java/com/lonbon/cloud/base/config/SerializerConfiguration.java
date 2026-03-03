package com.lonbon.cloud.base.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Component;
import org.noear.solon.serialization.snack4.Snack4StringSerializer;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 序列化配置类，用于定制序列化器的行为，用于控制接口返回的 JSON 字段格式
 * 1. 定制 OffsetDateTime 类型的序列化格式为 ISO 8601 格式
 * 参考：https://solon.noear.org/article/1200 solon-serialization-snack4 高级格式化定制
 */
@Component
public class SerializerConfiguration {

    @Bean
    public void config(Snack4StringSerializer serializer) {
        serializer.addEncoder(OffsetDateTime.class, s -> s.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
