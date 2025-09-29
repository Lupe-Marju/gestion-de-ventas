package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.dto.VentaDto;
import com.example.gestionDeVentas.model.Venta;
import com.example.gestionDeVentas.service.VentaService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private VentaService service;

    @PostMapping
    public ResponseEntity<String> registrarVenta(@RequestBody VentaDto ventaDto, Authentication authentication){
        service.registrarVenta(ventaDto, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Venta registrada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<VentaDto>> listarVentas(@RequestParam(required = false) Long sucursalId,
                                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
        List<VentaDto> ventas = service.obtenerVentas(Optional.ofNullable(sucursalId), Optional.ofNullable(fecha));
        return ResponseEntity.ok(ventas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id){
        service.eliminarVentaLogico(id);
        return ResponseEntity.ok("Venta eliminada correctamente");
    }
}
