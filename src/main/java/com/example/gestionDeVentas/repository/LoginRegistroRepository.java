package com.example.gestionDeVentas.repository;

import com.example.gestionDeVentas.model.LoginRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRegistroRepository  extends JpaRepository<LoginRegistro, Long> {
}