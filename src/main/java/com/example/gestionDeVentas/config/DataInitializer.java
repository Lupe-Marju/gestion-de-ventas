package com.example.gestionDeVentas.config;

import com.example.gestionDeVentas.model.Usuario;
import com.example.gestionDeVentas.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.findByUsername("user1").isEmpty()) {
                Usuario u1 = new Usuario();
                u1.setUsername("user1");
                u1.setPassword(passwordEncoder.encode("password"));
                usuarioRepository.save(u1);
            }
            if (usuarioRepository.findByUsername("user2").isEmpty()) {
                Usuario u2 = new Usuario();
                u2.setUsername("user2");
                u2.setPassword(passwordEncoder.encode("password"));
                usuarioRepository.save(u2);
            }
        };
    }

}
