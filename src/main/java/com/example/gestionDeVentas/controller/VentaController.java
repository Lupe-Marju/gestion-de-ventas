package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
    @Autowired
    private VentaService service;
}
