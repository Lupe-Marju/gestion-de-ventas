package com.example.gestionDeVentas.exception;

public class SucursalNotFoundException extends RuntimeException {
    public SucursalNotFoundException(String message) {
        super(message);
    }
}
