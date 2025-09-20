package com.example.gestionDeVentas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.rmi.ServerException;

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

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorRespuesta> handlerServerException(ServerException s) {
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                s.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorRespuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorRespuesta> handlerIOException(IOException io) {
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                HttpStatus.BAD_REQUEST.value(),
                io.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorRespuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorRespuesta> handlerUnauthorizedException(HttpClientErrorException.Unauthorized u) {
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                HttpStatus.UNAUTHORIZED.value(),
                u.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorRespuesta, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorRespuesta> handlerUsernameNotFoundException(UsernameNotFoundException u) {
        ErrorRespuesta errorRespuesta = new ErrorRespuesta(
                HttpStatus.NOT_FOUND.value(),
                u.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(errorRespuesta, HttpStatus.NOT_FOUND);
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