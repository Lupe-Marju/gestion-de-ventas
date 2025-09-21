package com.example.gestionDeVentas;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionDeVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeVentasApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("Gestón de ventas de un supermercado")
				.version("1.1")
				.description("La API ejecuta un CRUD de las sucursales, de los productos y de las ventas"));
	}

}
