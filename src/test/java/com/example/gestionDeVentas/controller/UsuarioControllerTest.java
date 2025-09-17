package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.controller.UsuarioController;
import com.example.gestionDeVentas.model.Usuario;
import com.example.gestionDeVentas.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void login_ok() throws Exception {
        Usuario u = new Usuario();
        u.setUsername("user1");
        u.setPassword("password");

        Mockito.when(usuarioService.comprobarUsuario(any(Usuario.class)))
                .thenReturn("fake-jwt-token");

        mockMvc.perform(post("/usuario/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk())
                .andExpect(content().string("fake-jwt-token"));
    }

    @Test
    void registrar_ok() throws Exception {
        Usuario u = new Usuario();
        u.setUsername("nuevo");
        u.setPassword("pass");

        Mockito.doNothing().when(usuarioService).registrar("nuevo", "pass");

        mockMvc.perform(post("/usuario/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario registrado correctamente"));
    }
}
