package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.dto.VentaDto;
import com.example.gestionDeVentas.dto.VentaItemDto;
import com.example.gestionDeVentas.exception.ProductoNotFoundException;
import com.example.gestionDeVentas.exception.SucursalNotFoundException;
import com.example.gestionDeVentas.model.Producto;
import com.example.gestionDeVentas.model.Sucursal;
import com.example.gestionDeVentas.model.Venta;
import com.example.gestionDeVentas.model.VentaItem;
import com.example.gestionDeVentas.repository.ProductoRepository;
import com.example.gestionDeVentas.repository.SucursalRepository;
import com.example.gestionDeVentas.repository.VentaItemRepository;
import com.example.gestionDeVentas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private VentaItemRepository ventaItemRepository;

    public VentaDto convertirVentaAVentaDto(Venta venta){
        VentaDto ventaDto = new VentaDto();
        ventaDto.setVentaId(venta.getId());
        ventaDto.setVentaSucursalId(venta.getSucursal().getId());
        for(VentaItem ventaItem : venta.getItems()){
ventaDto.getDetalle().add(new VentaItemDto(ventaItem.getProducto().getId(),ventaItem.getCantidad()));
        }
        return ventaDto;
    }

    @Transactional
    public void registrarVenta(VentaDto ventaDto) {
        if (ventaDto == null || ventaDto.getVentaSucursalId() == null || ventaDto.getVentaSucursalId()<0 || ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new IllegalArgumentException("Los campos ingresados no son correctos");
        Sucursal sucursal = sucursalRepository.findById(ventaDto.getVentaSucursalId())
                .orElseThrow(() -> new SucursalNotFoundException("La sucursal con id " + ventaDto.getVentaSucursalId() + " no fue encontrada"));

        Venta venta = new Venta();
        venta.setSucursal(sucursal);
        venta.setFechaDeCreacion(LocalDate.now());

        // crear items y agregar al objeto venta
        for (VentaItemDto itemDto : ventaDto.getDetalle()) {
            Producto producto = productoRepository.findById(itemDto.getProductoId())
                    .orElseThrow(() -> new ProductoNotFoundException("El producto con id " + itemDto.getProductoId() + " no fue encontrado"));
            if (itemDto.getCantidad() == null || itemDto.getCantidad() <= 0)
                throw new IllegalArgumentException("Cantidad inválida para producto " + producto.getId());

            VentaItem item = new VentaItem();
            item.setVenta(venta);
            item.setProducto(producto);
            item.setCantidad(itemDto.getCantidad());
            venta.getItems().add(item);
            ventaItemRepository.save(item);
        }
        ventaRepository.save(venta);
    }

    public List<VentaDto> obtenerVentas(Optional<Long> sucursalId, Optional<LocalDate> fecha) {
        if (sucursalId.isPresent() && fecha.isPresent()) {
            return ventaRepository.findBySucursalIdAndFechaDeCreacion(sucursalId.get(), fecha.get())
                    .stream()
                    .filter(a->!a.isEliminada())
                    .map(this::convertirVentaAVentaDto)
                    .toList();
        } else if (sucursalId.isPresent()) {
            return ventaRepository.findBySucursalId(sucursalId.get())
                    .stream()
                    .filter(a->!a.isEliminada())
                    .map(this::convertirVentaAVentaDto)
                    .toList();
        } else if (fecha.isPresent()) {
            return ventaRepository.findByFechaDeCreacion(fecha.get())
                    .stream()
                    .filter(a->!a.isEliminada())
                    .map(this::convertirVentaAVentaDto)
                    .toList();
        } else {
            return ventaRepository.findAll()
                    .stream()
                    .filter(a->!a.isEliminada())
                    .map(this::convertirVentaAVentaDto)
                    .toList();
        }
    }

    @Transactional
    public void eliminarVentaLogico(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("La venta con id " +id + " no fue encontrada"));
        venta.setEliminada(true);
        ventaRepository.save(venta);
    }

    // producto mas vendido (suma cantidades)
    public Optional<Map.Entry<ProductoDto, Integer>> productoMasVendido() {
        List<Venta> ventas = ventaRepository.findAll();
        Map<ProductoDto, Integer> totals = new HashMap<>();
        for (Venta v : ventas) {
            for (VentaItem item : v.getItems()) {
                Producto producto =  item.getProducto();
                    ProductoDto productoDto = new ProductoDto(producto.getId(),
                            producto.getNombre(),
                            producto.getPrecio(),
                            producto.getCategoria());
                    totals.put(productoDto, totals.getOrDefault(productoDto, 0) + item.getCantidad());

            }
        }
        return totals.entrySet().stream().max(Map.Entry.comparingByValue());

    }
}
