package ar.com.globallogic.challenge.kencinas95.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ResponseExceptionHandlerController
 * Controller for all the global exceptions in the application.
 */
@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandlerController {

    /**
     * Handle NoSuchElementException: 404
     *
     * @param t: exception
     * @return HTTP empty response (404 NOT FOUND)
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchException(final Throwable t) {
        log.error("Not found: {}", t.getMessage(), t);
        return ResponseEntity.notFound().build();
    }

    /**
     * Handle MethodArgumentNotValidException: 400
     *
     * @param ex: exception
     * @return HTTP response (json of errors) (400 BAD REQUEST)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException ex
    ) {
        log.error("Bad request: {}", ex.getLocalizedMessage(), ex);
        final Map<String, String> body = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(
                        Collectors.toMap(
                                FieldError::getField,
                                err -> Optional.ofNullable(err.getDefaultMessage()).orElse("invalid")
                        )
                );
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Handle IntegrityConstraintException: 409
     *
     * @param ex: exception
     * @return HTTP response (json of errors) (409 CONFLICT)
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, String>> handleIntegrityConstraintException(
            final DuplicateKeyException ex
    ) {
        log.error("Conflict: {}", ex.getMessage(), ex);
        final Map<String, String> body = Map.of(
                "duplicated",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

}
