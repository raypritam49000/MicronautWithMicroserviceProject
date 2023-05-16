package com.user.service.api.exceptions;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Factory
public class ExceptionHandlerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerFactory.class);

    @Bean
    @Singleton
    public ExceptionHandler<ResourceNotFound, Map<String, Object>> noSuchElementExceptionHandler() {
        return (request, exception) -> {
            LOGGER.error("Caught NoSuchElementException: ", exception);
            return Map.of("status","SUCCESS","statusCode",404,"message", exception.getMessage());
        };
    }
}
