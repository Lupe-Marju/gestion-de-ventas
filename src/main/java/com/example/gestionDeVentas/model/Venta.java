package com.example.gestionDeVentas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name="ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaDeCreacion;
    private boolean eliminada;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToMany
   @JoinTable(name = "producto_venta",
           joinColumns = @JoinColumn(name = "producto_id"),
           inverseJoinColumns = @JoinColumn(name = "venta_id")
   )
    private List<Producto> productosVendidos = new ArrayList<>();
}
