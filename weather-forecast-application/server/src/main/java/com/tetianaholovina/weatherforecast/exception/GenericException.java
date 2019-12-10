package com.tetianaholovina.weatherforecast.exception;

import com.tetianaholovina.weatherforecast.model.error.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GenericException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Validation failed",
                ex.getBindingResult().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public final ResponseEntity<ErrorDetails> validationException(Exception ex, WebRequest request){
        return new ResponseEntity<>(getErrorDetails(ex, request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {WeatherForecastException.class, Exception.class})
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(getErrorDetails(ex, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DataNotFoundException.class})
    public final ResponseEntity<ErrorDetails> handleResourceNotFoundException(Exception ex, WebRequest request){
        return new ResponseEntity<>(getErrorDetails(ex, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public final ResponseEntity<ErrorDetails> handleUnauthorizedException(Exception ex, WebRequest request){
        return new ResponseEntity<>(getErrorDetails(ex, request), HttpStatus.UNAUTHORIZED);
    }

    private ErrorDetails getErrorDetails(Exception ex, WebRequest request){
        return new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    }
}
