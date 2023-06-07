package com.example.secondhand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleCustomerNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SellerNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleSellerNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleAdminNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ColorNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleColorNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ComputerAccessoriesNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleComputerAccessoriesNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CpuNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleCpuNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GamingConsoleNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleGamingConsoleNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GpuNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleGpuNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleOrderNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handlePhoneNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PcNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handlePcNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductBrandNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleProductBrandNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleProductNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handleException(GenericException exception) {
        Map<String, Object> errors = new HashMap<>();
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(errors);
    }

}