package com.example.gestionDeVentas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {
    private Long idProducto;
    private String nombreProducto;
    private Double precioProducto;
    private String categoriaProducto;
}
