package com.example.gestionDeVentas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class VentaDto {
    private Long productoId;
    private String nombreProducto;
    private Map<String,Integer> productosVendidos = new HashMap<>();
}
