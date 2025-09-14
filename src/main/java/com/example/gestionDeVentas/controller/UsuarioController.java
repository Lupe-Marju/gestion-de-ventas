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
    private JwtService jwtService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        // pasar comprobacion a capa service
        // mañana Comprobar esto...
        try {
            boolean valido = usuarioService.autenticar(usuario.getUsername(), usuario.getPassword());
            if (valido) {
                String token = jwtService.generarToken(usuario.getUsername());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.registrar(usuario.getUsername(), usuario.getPassword());
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}