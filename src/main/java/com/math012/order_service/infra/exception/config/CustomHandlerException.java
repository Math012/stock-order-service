package com.math012.order_service.infra.exception.config;

import com.math012.order_service.infra.exception.ProductIsNotInStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductIsNotInStockException.class)
    public ResponseEntity<StructException> handlerProductIsNotInStockException(Exception e, WebRequest request){
        var response = new StructException(e.getMessage(),new Date(),request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}