package com.example.gestionDeVentas.controller;


import com.example.gestionDeVentas.model.Producto;
import com.example.gestionDeVentas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<?> productoMasVendido() {
        Optional<Map.Entry<Producto, Integer>> top = ventaService.productoMasVendido();
        if (top.isPresent()) {
            var entry = top.get();
            return ResponseEntity.ok(Map.of(
                    "producto", entry.getKey(),
                    "cantidadVendida", entry.getValue()
            ));
        }
        return ResponseEntity.ok(Map.of("mensaje", "No hay ventas registradas"));
    }
}
