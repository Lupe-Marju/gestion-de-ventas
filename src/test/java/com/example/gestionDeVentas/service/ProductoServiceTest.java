package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.model.Producto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProductoServiceTest {

    ProductoService productoService = new ProductoService();

    @Test
    public void testConvertirDtoaProducto() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", 2.1, "Dulcecito",10);
        assertEquals("Choco", productoService.convertirDtoAProducto(productoDto).getNombre());
        assertEquals(2.1, productoService.convertirDtoAProducto(productoDto).getPrecio());
        assertEquals("Dulcecito", productoService.convertirDtoAProducto(productoDto).getCategoria());
    }

    @Test
    public void testConvertirProductoADto() {
        Producto producto = new Producto(1L, "Choco", 2.1, "Dulcecito", 10,false);
        assertEquals("Choco", productoService.convertirProductoADto(producto).getNombreProducto());
        assertEquals(2.1, productoService.convertirProductoADto(producto).getPrecioProducto());
        assertEquals("Dulcecito", productoService.convertirProductoADto(producto).getCategoriaProducto());
    }

    @Test
    public void testAgregarProductoNombreBlank() {
        ProductoDto productoDto = new ProductoDto(1L, "", 2.1, "Dulce",10);
        assertThrows(IllegalArgumentException.class, () -> productoService.agregarProducto(productoDto));
    }

    @Test
    public void testAgregarProductoCategoriaBlank() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", 2.1, "",10);
        assertThrows(IllegalArgumentException.class, () -> productoService.agregarProducto(productoDto));
    }

    @Test
    public void testAgregarProductoPrecioNegativo() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", -1., "Dulce",10);
        assertThrows(IllegalArgumentException.class, () -> productoService.agregarProducto(productoDto));
    }

    @Test
    public void testAgregarProductoCantidadNegativa() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", -1., "Dulce",-10);
        assertThrows(IllegalArgumentException.class, () -> productoService.agregarProducto(productoDto));
    }

    @Test
    public void testAgregarProductoCantidadNula() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", -1., "Dulce",null);
        assertThrows(IllegalArgumentException.class, () -> productoService.agregarProducto(productoDto));
    }

    @Test
    public void testActualizarProductoNombreBlank() {
        ProductoDto productoDto = new ProductoDto(1L, "", 2.1, "Dulce",10);
        assertThrows(IllegalArgumentException.class, () -> productoService.actualizarProducto(productoDto, 1L));
    }

    @Test
    public void testActualizarProductoNombreNull() {
        ProductoDto productoDto = new ProductoDto(1L, null, 2.1, "Dulce",10);
        assertThrows(IllegalArgumentException.class, () -> productoService.actualizarProducto(productoDto, 1L));
    }

    @Test
    public void testActualizarProductoCategoriaBlank() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", 2.1, "",10);
        assertThrows(IllegalArgumentException.class, () -> productoService.actualizarProducto(productoDto, 1L));
    }

    @Test
    public void testActualizarProductoCategoriaNull() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", 2.1, null,10);
        assertThrows(IllegalArgumentException.class, () -> productoService.actualizarProducto(productoDto, 1L));
    }

    @Test
    public void testActualizarProductoPrecioNegativo() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", -1., "Dulce",10);
        assertThrows(IllegalArgumentException.class, () -> productoService.actualizarProducto(productoDto, 1L));
    }

    @Test
    public void testActualizarProductoPrecioNulo() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", null, "Dulce",10);
        assertThrows(IllegalArgumentException.class, () -> productoService.actualizarProducto(productoDto, 1L));
    }

    @Test
    public void testActualizarProductoCantidadNegativa() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", -1., "Dulce",-10);
        assertThrows(IllegalArgumentException.class, () -> productoService.actualizarProducto(productoDto,1L));
    }

    @Test
    public void testActualizarProductoCantidadNula() {
        ProductoDto productoDto = new ProductoDto(1L, "Choco", -1., "Dulce",null);
        assertThrows(IllegalArgumentException.class, () -> productoService.actualizarProducto(productoDto,1L));
    }
}
