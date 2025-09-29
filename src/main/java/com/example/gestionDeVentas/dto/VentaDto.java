package com.example.gestionDeVentas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDto {
    private Long ventaId;
    private Long ventaSucursalId;
    private String usuarioCreador;
    private List<VentaItemDto> detalle = new ArrayList<>();
}
