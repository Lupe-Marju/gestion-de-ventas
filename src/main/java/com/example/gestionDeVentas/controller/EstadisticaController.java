package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<?> productoMasVendido(@RequestParam(defaultValue = "5") int topN) {
        List<AbstractMap.SimpleEntry<ProductoDto, Integer>> topProductos = ventaService.productoMasVendido(topN);
        if (topProductos.isEmpty()) {
            return ResponseEntity.ok(Map.of("mensaje", "No hay ventas registradas"));
        }
        List<Map<String, Object>> response = topProductos.stream()
                .map(entry -> Map.of(
                        "producto", entry.getKey(),
                        "cantidadVendida", entry.getValue()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}