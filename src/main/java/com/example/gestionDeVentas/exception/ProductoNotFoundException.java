package com.example.gestionDeVentas.exception;

public class ProductoNotFoundException extends RuntimeException {
  public ProductoNotFoundException(String message) {
    super(message);
  }
}
