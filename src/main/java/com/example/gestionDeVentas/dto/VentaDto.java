package com.example.gestionDeVentas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class VentaDto {
    private Long ventaId;
    private Long ventaSucursalId;
    private Map<Long, Integer> productosVendidos = new HashMap<>();
}
