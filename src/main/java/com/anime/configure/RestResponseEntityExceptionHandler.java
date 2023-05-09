package com.anime.configure;

import com.anime.domain.AnimeException;
import com.anime.domain.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AnimeException.class})
    protected ResponseEntity<Object> handleConflict(
            AnimeException ex, WebRequest request) {
        return ResponseEntity.ok(new Result<>(Const.FAIL, ex.getMessage()));
    }
}
