package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.dto.LoginRegistroDto;
import com.example.gestionDeVentas.dto.UsuarioDto;
import com.example.gestionDeVentas.repository.LoginRegistroRepository;
import com.example.gestionDeVentas.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LoginRegistroRepository loginRegistroRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDto usuarioDto, HttpServletRequest request) {
        // delega en service la comprobación y generación del token
        String token = usuarioService.login(usuarioDto, request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        usuarioService.registrar(usuarioDto.getMyUsername(), usuarioDto.getMyPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
    }

    @GetMapping("/logins")
    public ResponseEntity<List<LoginRegistroDto>> obtenerLogins() {
        return ResponseEntity.ok(usuarioService.obtenerLogins());
    }
}