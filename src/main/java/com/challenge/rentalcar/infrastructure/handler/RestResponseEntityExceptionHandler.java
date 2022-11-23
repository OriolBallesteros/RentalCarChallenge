package com.challenge.rentalcar.infrastructure.handler;

import com.challenge.rentalcar.domain.exception.DateOutOfBoundException;
import com.challenge.rentalcar.domain.exception.NotFoundCarException;
import com.challenge.rentalcar.domain.exception.NotFoundClientException;
import com.challenge.rentalcar.domain.exception.NotFoundModelException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundCarException.class)
  protected ResponseEntity<Object> handleNotFoundCarException(final NotFoundCarException ex,
      final WebRequest request) {
    final Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NotFoundModelException.class)
  protected ResponseEntity<Object> handleNotFoundModelException(final NotFoundModelException ex,
      final WebRequest request) {
    final Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DateOutOfBoundException.class)
  protected ResponseEntity<Object> handleDateOutOfBoundException(final DateOutOfBoundException ex,
      final WebRequest request) {
    final Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(NotFoundClientException.class)
  protected ResponseEntity<Object> handleNotFoundClientException(final NotFoundClientException ex,
      final WebRequest request) {
    final Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }
}

