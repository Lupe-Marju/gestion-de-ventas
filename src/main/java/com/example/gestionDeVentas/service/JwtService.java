package com.example.gestionDeVentas.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expirationMillis;

    public JwtService(@Value("${jwt.secret}") String key, @Value("${jwt.expirationMillis}") long expirationMillis) {
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes());
        this.expirationMillis = expirationMillis;
    }

    /*String key = "estaeslaclaveseguraysecreta123456789";
    SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());*/
    public String generarToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1800000))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public String validarToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey) // Indica al parser cual fue la clave que usamos al crear el token
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
