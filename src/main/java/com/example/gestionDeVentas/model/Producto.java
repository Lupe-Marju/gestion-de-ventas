package com.example.gestionDeVentas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String nombre;
    private Double precio;
    private String categoria;

    @ManyToMany(mappedBy = "productosVendidos")
   private List<Venta> ventas = new ArrayList<>();
    @ElementCollection
    private Map<Long,Integer> cantidadDeProductoVendido =new HashMap<>();

}
