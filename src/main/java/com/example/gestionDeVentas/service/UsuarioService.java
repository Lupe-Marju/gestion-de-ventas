package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.LoginRegistroDto;
import com.example.gestionDeVentas.dto.UsuarioDto;
import com.example.gestionDeVentas.model.LoginRegistro;
import com.example.gestionDeVentas.model.Usuario;
import com.example.gestionDeVentas.repository.LoginRegistroRepository;
import com.example.gestionDeVentas.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginRegistroRepository loginRegistroRepository;

    // Registramos usuario con contraseña encriptada
    public void registrar(String username, String password) {
        if (username == null ||
                username.isBlank() ||
                password == null ||
                password.isBlank()) {
            throw new IllegalArgumentException("Los campos ingresados no son correctos");
        }
        if (repository.existsByUsername(username)) {
            throw new IllegalArgumentException("Ese nombre de usuario ya se encuentra utilizado.");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));// encriptamos la contraseña
        repository.save(usuario);
    }

    // Autenticamos el usuario comparando la contraseña encriptada
    public boolean autenticar(String username, String passwordIngresada) {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuaio no encontrado"));
        return passwordEncoder.matches(passwordIngresada, usuario.getPassword());
    }

    public String comprobarUsuario(UsuarioDto usuarioDto) {
        boolean valido = autenticar(usuarioDto.getMyUsername(), usuarioDto.getMyPassword());
        if (valido) {
            return jwtService.generarToken(usuarioDto.getMyUsername());
        } else {
            throw new IllegalArgumentException("Contraseña no valida");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }

    public String login(UsuarioDto usuarioDto, HttpServletRequest request) {
        String token = comprobarUsuario(usuarioDto);
        LoginRegistro registro = LoginRegistro.builder()
                .username(usuarioDto.getMyUsername())
                .fechaHora(LocalDateTime.now())
                .exito(true)
                .build();
        loginRegistroRepository.save(registro);
        return token;
    }

    public List<LoginRegistroDto> obtenerLogins() {
        return loginRegistroRepository.findAll().stream()
                .map(registro -> LoginRegistroDto.builder()
                        .registroUsername(registro.getUsername())
                        .registroFechaHora(registro.getFechaHora())
                        .registroExito(registro.isExito())
                        .build())
                .toList();
    }
}
