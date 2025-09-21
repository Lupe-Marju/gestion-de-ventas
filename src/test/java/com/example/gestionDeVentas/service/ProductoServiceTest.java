package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductoServiceTest {

    ProductoService productoService = new ProductoService();

    @Test
    public void testConvertirDtoaProducto() {
        ProductoDto productoDto = new ProductoDto(1L,"Choco", 2.1, "Dulcecito");
        assertEquals("Choco", productoService.convertirDtoAProducto(productoDto).getNombre());
        assertEquals(2.1,productoService.convertirDtoAProducto(productoDto).getPrecio());
        assertEquals("Dulcecito",productoService.convertirDtoAProducto(productoDto).getCategoria());
    }

}
