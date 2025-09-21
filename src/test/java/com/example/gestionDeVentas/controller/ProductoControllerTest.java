package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.controller.ProductoController;
import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductosOk() throws Exception {
        ProductoDto p = new ProductoDto(1L,"Arroz",100.0,"Alimentos");
        Mockito.when(productoService.listar()).thenReturn(List.of(p));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void crearProducto_ok() throws Exception {
        ProductoDto dto = new ProductoDto(null, "Leche", 50.0, "Lacteos");
        Mockito.doNothing().when(productoService).agregarProducto(any(ProductoDto.class));

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}