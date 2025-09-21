package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.dto.LoginRegistroDto;
import com.example.gestionDeVentas.dto.UsuarioDto;
import com.example.gestionDeVentas.model.LoginRegistro;
import com.example.gestionDeVentas.repository.LoginRegistroRepository;
import com.example.gestionDeVentas.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        String token = usuarioService.comprobarUsuario(usuarioDto);

        LoginRegistro registro = LoginRegistro.builder()
                .username(usuarioDto.getMyUsername())
                .fechaHora(LocalDateTime.now())
                .exito(true)
                .build();
        loginRegistroRepository.save(registro);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        usuarioService.registrar(usuarioDto.getMyUsername(), usuarioDto.getMyPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
    }

    @GetMapping("/logins")
    public ResponseEntity<List<LoginRegistroDto>> obtenerLogins(){
        List<LoginRegistroDto> registros = loginRegistroRepository.findAll().stream()
                .map(registro->LoginRegistroDto.builder()
                        .registroUsername(registro.getUsername())
                        .registroFechaHora(registro.getFechaHora())
                        .registroExito(registro.isExito())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(registros);
    }
}