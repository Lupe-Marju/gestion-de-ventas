package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository repository;
}
