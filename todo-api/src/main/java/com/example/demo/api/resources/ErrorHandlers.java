package com.example.demo.api.resources;

import com.example.demo.api.dto.ApiErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.NotAcceptableStatusException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ErrorHandlers {

    private final boolean includeStackTrace;

    public ErrorHandlers(@Value("${server.error.include-stacktrace:never}") ErrorProperties.IncludeAttribute includeStackTrace) {
        this.includeStackTrace = ErrorProperties.IncludeAttribute.ALWAYS == includeStackTrace;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorDto> handleRuntimeException(RuntimeException ex) {
        var error = createApiError("Internal Server Error", ex);

        log.error(ex.getMessage(), ex);

        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(NotAcceptableStatusException.class)
    public ResponseEntity<ApiErrorDto> handleNotAcceptableStatusException(NotAcceptableStatusException ex) {
        var error = createApiError("Not Acceptable", ex);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDto> handleConstraintViolationException(ConstraintViolationException ex) {
        var error = createApiError("Bad Request", ex);

        log.warn("Constraint validation: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private ApiErrorDto createApiError(String error, Throwable t) {
        var stackTrace = this.includeStackTrace ? buildStackTrace(t) : null;

        return new ApiErrorDto(
                error,
                t.getLocalizedMessage(),
                stackTrace);
    }

    private static List<String> buildStackTrace(Throwable t) {
        return Arrays.stream(t.getStackTrace())
                .map(StackTraceElement::toString)
                .toList();
    }
}
