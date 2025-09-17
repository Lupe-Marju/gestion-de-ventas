package com.example.gestionDeVentas.repository;

import com.example.gestionDeVentas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findBySucursalId(Long sucursalId);
    List<Venta> findByFechaDeCreacion(LocalDate fecha);
    List<Venta> findBySucursalIdAndFechaDeCreacion(Long sucursalId,LocalDate fecha);
    // List<Venta> findBySucursalIdAndFecha(Long sucursalid, LocalDate fecha);
    // List<Venta> fibdByFecha(LocalDate fecha);
}
