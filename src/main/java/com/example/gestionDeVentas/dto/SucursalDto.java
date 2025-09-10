package com.example.gestionDeVentas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SucursalDto {
    private Long idSucursal;
    private String nombreSucursal;
    private String direccionSucursal;
}
