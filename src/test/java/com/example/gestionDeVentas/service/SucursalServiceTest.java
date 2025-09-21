package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.SucursalDto;
import com.example.gestionDeVentas.model.Sucursal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SucursalServiceTest {

    SucursalService sucursalService = new SucursalService();

    @Test
    void testConvertirSucursalDto() {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("sud");
        sucursal.setId(1L);
        sucursal.setDireccion("misiones 21");
        assertEquals("sud", sucursalService.convertirSucursalaDto(sucursal).getNombreSucursal());
        assertEquals(1L, sucursalService.convertirSucursalaDto(sucursal).getIdSucursal());
        assertEquals("misiones 21", sucursalService.convertirSucursalaDto(sucursal).getDireccionSucursal());
    }

    @Test
    void testCrearSucursalNombreBlanck() {
        SucursalDto sucursalDto = new SucursalDto(null, "", "mision 12");
        assertThrows(IllegalArgumentException.class, () -> sucursalService.crearSucursal(sucursalDto));
    }

    @Test
    void testCrearSucursalNombreNulo() {
        SucursalDto sucursalDto = new SucursalDto(null, null, "mision 12");
        assertThrows(IllegalArgumentException.class, () -> sucursalService.crearSucursal(sucursalDto));
    }

    @Test
    void testCrearSucursalDireccionBlanck() {
        SucursalDto sucursalDto = new SucursalDto(null, "norte", "");
        assertThrows(IllegalArgumentException.class, () -> sucursalService.crearSucursal(sucursalDto));
    }

    @Test
    void testCrearSucursalDireccionNulo() {
        SucursalDto sucursalDto = new SucursalDto(null, "norte", null);
        assertThrows(IllegalArgumentException.class, () -> sucursalService.crearSucursal(sucursalDto));
    }

    @Test
    void testActualizarSucursalNombreBlanck() {
        SucursalDto sucursalDto = new SucursalDto(null, "", "mision 12");
        assertThrows(IllegalArgumentException.class, () -> sucursalService.actualizarSucursal(sucursalDto,1L));
    }

    @Test
    void testActualizarSucursalNombreNulo() {
        SucursalDto sucursalDto = new SucursalDto(null, null, "mision 12");
        assertThrows(IllegalArgumentException.class, () -> sucursalService.actualizarSucursal(sucursalDto,1L));
    }

    @Test
    void testActualizarSucursalDireccionBlanck() {
        SucursalDto sucursalDto = new SucursalDto(null, "norte", "");
        assertThrows(IllegalArgumentException.class, () -> sucursalService.actualizarSucursal(sucursalDto,1L));
    }

    @Test
    void testActualizarSucursalDireccionNulo() {
        SucursalDto sucursalDto = new SucursalDto(null, "norte", null);
        assertThrows(IllegalArgumentException.class, () -> sucursalService.actualizarSucursal(sucursalDto,1L));
    }

}
