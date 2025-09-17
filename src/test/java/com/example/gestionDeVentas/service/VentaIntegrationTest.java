package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.VentaDto;
import com.example.gestionDeVentas.dto.VentaItemDto;
import com.example.gestionDeVentas.model.Producto;
import com.example.gestionDeVentas.model.Sucursal;
import com.example.gestionDeVentas.repository.ProductoRepository;
import com.example.gestionDeVentas.repository.SucursalRepository;
import com.example.gestionDeVentas.repository.VentaRepository;
import com.example.gestionDeVentas.service.VentaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class VentaIntegrationTest {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private VentaRepository ventaRepository;

    @Test
    @Transactional
    void registrarVenta_persisteCorrectamente_y_rollbackAlFinal() {
        // crear sucursal y producto
        Sucursal s = new Sucursal();
        s.setNombre("S1"); s.setDireccion("Calle 1");
        s = sucursalRepository.save(s);

        Producto p = new Producto();
        p.setNombre("Azucar"); p.setPrecio(80.0); p.setCategoria("Alimentos");
        p = productoRepository.save(p);

        VentaItemDto item = new VentaItemDto(p.getId(), 2);
        VentaDto ventaDto = new VentaDto(null, s.getId(), List.of(item));

        ventaService.registrarVenta(ventaDto);

        Assertions.assertFalse(ventaRepository.findAll().isEmpty());
        // al finalizar la transacción de test, se hace rollback automáticamente por @Transactional
    }
}
