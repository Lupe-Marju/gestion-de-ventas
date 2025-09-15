package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.model.Usuario;
import com.example.gestionDeVentas.service.JwtService;
import com.example.gestionDeVentas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        // pasar comprobacion a capa
        return ResponseEntity.ok(usuarioService.comprobarUsuario(usuario));
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.registrar(usuario.getUsername(), usuario.getPassword());
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}