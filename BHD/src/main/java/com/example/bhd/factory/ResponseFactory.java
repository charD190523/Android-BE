package com.example.bhd.factory;

import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Configuration
public class ResponseFactory {

    public static <T> GeneralResponse<T> success(T data) {
        StatusDTO status = new StatusDTO();
        status.setCode("00");
        status.setMessage("Thành công.");
        status.setResponseTime(Instant.now().atZone(ZoneOffset.UTC).toString());
        status.setDisplayMessage("Thành công.");

        return new GeneralResponse<>(status, data);
    }

    public static <T> GeneralResponse<T> fail(String message) {
        StatusDTO status = new StatusDTO();
        status.setCode("99");
        status.setMessage(message);
        status.setResponseTime(Instant.now().atZone(ZoneOffset.UTC).toString());
        status.setDisplayMessage(message);

        return new GeneralResponse<>(status, null);
    }
}

