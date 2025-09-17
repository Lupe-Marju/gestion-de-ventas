package com.example.gestionDeVentas.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorRespuesta {
    private int status;
    private String mensaje;
    private long timestap;
}
