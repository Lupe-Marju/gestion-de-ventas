package com.example.gestionDeVentas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRegistroDto {
    private String registroUsername;
    private LocalDateTime registroFechaHora;
    private boolean registroExito;
}
