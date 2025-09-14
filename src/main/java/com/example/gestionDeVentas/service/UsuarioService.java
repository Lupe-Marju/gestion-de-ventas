package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.model.Usuario;
import com.example.gestionDeVentas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;
    //Registramos usuario con contraseña encriptada
    public void registrar(String username, String password){
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));//encriptamos la contraseña
        repository.save(usuario);
    }
    //Autenticamos el usuario comparando la contraseña encriptada
    public boolean autenticar (String username, String passwordIngresada){
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuaio no encontrado"));
        return passwordEncoder.matches(passwordIngresada, usuario.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }
}
