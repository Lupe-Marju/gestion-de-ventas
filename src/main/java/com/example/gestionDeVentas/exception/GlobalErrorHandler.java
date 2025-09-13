package com.example.gestionDeVentas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorRespuesta> handlerProductoNotFoundException(ProductoNotFoundException p) {
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                HttpStatus.NOT_FOUND.value(),
                p.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorRespuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SucursalNotFoundException.class)
    public ResponseEntity<ErrorRespuesta> handlerSucursalNotFoundException(SucursalNotFoundException s) {
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                HttpStatus.NOT_FOUND.value(),
                s.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorRespuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorRespuesta> handlerIllegalArgumentException(IllegalArgumentException i) {
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                i.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorRespuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespuesta> handlerException(Exception exception){
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorRespuesta,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}