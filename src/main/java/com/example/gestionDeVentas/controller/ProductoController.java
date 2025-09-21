package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<String> agregarProducto(@RequestBody ProductoDto productoDto) {
        service.agregarProducto(productoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(@RequestBody ProductoDto productoDto,
                                                     @PathVariable Long id) {
        service.actualizarProducto(productoDto, id);
        return ResponseEntity.ok("Producto actualizado correctamente");
    }

    @PatchMapping("/{id}/{cantidadNueva}")
    public ResponseEntity<String> actualizarProducto(@PathVariable Long id,
                                                     @PathVariable Integer cantidadNueva) {
        service.actualizarCantidadDeProducto(cantidadNueva, id);
        return ResponseEntity.ok("Cantidad de producto agregada correctamente");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}
