package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.model.Producto;
import com.example.gestionDeVentas.model.Venta;
import com.example.gestionDeVentas.model.VentaItem;
import com.example.gestionDeVentas.repository.VentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.AbstractMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaService ventaService;

    private Producto arroz;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        arroz = new Producto(1L, "Arroz", 100.0, "Alimentos", 10, false);
    }

    @Test
    void productoMasVendidoOk() {
        // venta con item
        Venta v = new Venta();
        VentaItem item = new VentaItem();
        item.setProducto(arroz);
        item.setCantidad(5);
        v.getItems().add(item);

        when(ventaRepository.findByEliminadaFalse()).thenReturn(List.of(v));

        List<AbstractMap.SimpleEntry<ProductoDto, Integer>> resultado = ventaService.productoMasVendido(1);

        assertEquals(1, resultado.size());
        assertEquals("Arroz", resultado.get(0).getKey().getNombreProducto());
        assertEquals(5, resultado.get(0).getValue());
    }

    @Test
    void productoMasVendidoParametroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> ventaService.productoMasVendido(0));
    }
}
