package br.com.challengedropbox.commons.exceptions;

import br.com.challengedropbox.commons.enums.ErrorException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class AppException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorException error;
    private final List<String> errors;

    protected AppException(ErrorException error, HttpStatus httpStatus, List<String> errors, Object... args) {
        super(error.format(args));
        this.error = error;
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public ResponseEntity<Object> toResponseEntity() {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", this.httpStatus.value());
        body.put("error", this.httpStatus.getReasonPhrase());
        body.put("message", this.getMessage());
        body.put("errors", this.errors);
        body.put("path", "/user-service/v1/users");

        return new ResponseEntity<>(body, this.httpStatus);
    }

}
