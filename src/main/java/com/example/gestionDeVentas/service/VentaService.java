package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {
    @Autowired
    private VentaRepository repository;
}
