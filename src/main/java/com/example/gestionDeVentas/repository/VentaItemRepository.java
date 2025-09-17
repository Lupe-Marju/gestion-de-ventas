package com.example.gestionDeVentas.repository;

import com.example.gestionDeVentas.model.VentaItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaItemRepository extends JpaRepository<VentaItem, Long> {
}
