package com.example.gestionDeVentas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDto {
    private Long ventaId;
    private Long ventaSucursalId;
    private List<VentaItemDto> detalle;
}
