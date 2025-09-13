package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.dto.VentaDto;
import com.example.gestionDeVentas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private VentaService service;

    @PostMapping
    public ResponseEntity<String> registrarVenta(@RequestBody VentaDto ventaDto){
        service.registrarVenta(ventaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venta registrada correctamente");
    }
}
