package com.example.gestionDeVentas.controller;

import com.example.gestionDeVentas.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SucursalController {
    @Autowired
    private SucursalService service;
}
