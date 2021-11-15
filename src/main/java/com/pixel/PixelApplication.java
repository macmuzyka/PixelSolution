package com.pixel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PixelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PixelApplication.class, args);
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return mapper;
    }
//    @Bean
//    public Formatter<LocalDate> localDateFormatter() {
//        return new Formatter<LocalDate>() {
//            @Override
//            public LocalDate parse(String text, Locale locale) throws ParseException {
//                return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
//            }
//
//            @Override
//            public String print(LocalDate object, Locale locale) {
//                return DateTimeFormatter.ISO_DATE.format(object);
//            }
//        };
//    }
}
