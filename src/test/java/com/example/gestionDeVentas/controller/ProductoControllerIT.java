package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.service.JwtService;
import com.example.gestionDeVentas.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductoController.class)
@AutoConfigureMockMvc(addFilters = false)  // desactiva filtros de seguridad
public class ProductoControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @MockitoBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductosOk() throws Exception {
        ProductoDto p = new ProductoDto(1L,"Arroz",100.0,"Alimentos",10);
        Mockito.when(productoService.listar()).thenReturn(List.of(p));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idProducto").value(1))
                .andExpect(jsonPath("$[0].nombreProducto").value("Arroz"))
                .andExpect(jsonPath("$[0].precioProducto").value(100.0))
                .andExpect(jsonPath("$[0].categoriaProducto").value("Alimentos"));
    }

    @Test
    void crearProductoOk() throws Exception {
        ProductoDto dto = new ProductoDto(null, "Leche", 50.0, "Lacteos",10);
        Mockito.doNothing().when(productoService).agregarProducto(any(ProductoDto.class));

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        Mockito.verify(productoService, Mockito.times(1)).agregarProducto(any(ProductoDto.class));
    }
}