package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.GestionDeVentasApplication;
import com.example.gestionDeVentas.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = GestionDeVentasApplication.class)
@AutoConfigureMockMvc
@Transactional

public class UsuarioControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void login_ok() throws Exception {
        Usuario u = new Usuario();
        u.setUsername("user1");
        u.setPassword("password");
        mockMvc.perform(post("/usuario/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isCreated());


        mockMvc.perform(post("/usuario/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk());}

    @Test
    void registrar_ok() throws Exception {
        Usuario u = new Usuario();
        u.setUsername("nuevo");
        u.setPassword("pass");

        mockMvc.perform(post("/usuario/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario registrado correctamente"));
    }
}
