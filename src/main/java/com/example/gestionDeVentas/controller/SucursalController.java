package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.dto.SucursalDto;
import com.example.gestionDeVentas.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService service;

    @GetMapping
    public ResponseEntity<List<SucursalDto>> listarSucursales(){
        return ResponseEntity.ok(service.listarSucursales());
    }

    @PostMapping
    public ResponseEntity<String> crearSucursal(@RequestBody SucursalDto sucursalDto){
        service.crearSucursal(sucursalDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sucursal creada correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarSucursal(@PathVariable Long id, @RequestBody SucursalDto sucursalDto){
        service.actualizarSucursal(sucursalDto, id);
        return ResponseEntity.ok("Sucursal actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSucursal(@PathVariable Long id){
        service.eliminarsucursal(id);
        return ResponseEntity.ok("Sucursal eliminada correctamente");
    }
}
