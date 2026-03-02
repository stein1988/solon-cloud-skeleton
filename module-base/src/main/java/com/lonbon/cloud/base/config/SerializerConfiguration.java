package com.lonbon.cloud.base.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Component;
import org.noear.solon.serialization.snack4.Snack4StringSerializer;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SerializerConfiguration {


    @Bean
    public void config(Snack4StringSerializer serializer) {
        serializer.addEncoder(OffsetDateTime.class, s -> s.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
