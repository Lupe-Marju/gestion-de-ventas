package com.example.gestionDeVentas.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorRespuesta {
    private int status;
    private String mensaje;
    private long timestap;
}
