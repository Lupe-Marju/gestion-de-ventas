package com.example.gestionDeVentas.repository;

import com.example.gestionDeVentas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {
}
