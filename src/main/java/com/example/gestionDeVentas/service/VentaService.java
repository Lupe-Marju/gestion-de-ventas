package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.VentaDto;
import com.example.gestionDeVentas.exception.SucursalNotFoundException;
import com.example.gestionDeVentas.model.Venta;
import com.example.gestionDeVentas.repository.ProductoRepository;
import com.example.gestionDeVentas.repository.SucursalRepository;
import com.example.gestionDeVentas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public void registrarVenta(VentaDto ventaDto) {
        Venta venta = new Venta();
        venta.setSucursal(sucursalRepository.findById(ventaDto.getVentaSucursalId())
                .orElseThrow(() -> new SucursalNotFoundException("La sucursal con id " + ventaDto.getVentaSucursalId() + " no fue encontrada")));
        venta.setFechaDeCreacion(LocalDate.now());
        venta.setEliminada(false);
    }
}
