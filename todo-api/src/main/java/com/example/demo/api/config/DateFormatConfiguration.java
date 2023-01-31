package com.example.demo.api.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class DateFormatConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonDateTimeCustomizer() {
        return builder -> {
            builder.timeZone(TimeZone.getTimeZone("UTC"));
            builder.simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ISO_DATE));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
            builder.serializers(ZonedDateTimeSerializer.INSTANCE);
            builder.serializers(OffsetDateTimeSerializer.INSTANCE);
        };
    }
}
