package com.example.gestionDeVentas.repository;

import com.example.gestionDeVentas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
