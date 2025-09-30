Gestión de Ventas: Supermercados

Plataforma desarrollada con Spring Boot 3, diseñada para la digitalización integral del proceso de ventas en una cadena de supermercados.

Introducción
El presente proyecto implementa una API de gestión de ventas que permite a una cadena de supermercados administrar:
    • Productos (inventario, precios, categorías)
    • Sucursales (ubicaciones físicas)
    • Ventas (detalle por producto y sucursal)
    • Estadísticas (productos más vendidos, ingresos por sucursal)
    • Usuarios (registro, login y autenticación mediante JWT)
Todo con un enfoque en escalabilidad, seguridad y buenas prácticas REST.

Funcionalidades Clave
    • Gestión de productos: CRUD con validaciones de negocio.
    • Gestión de sucursales: registro y mantenimiento de ubicaciones.
    • Registro de ventas: asociadas a usuario y sucursal, con control de stock.
    • Estadísticas avanzadas: consulta de productos más vendidos.
    • Autenticación JWT: acceso restringido según tipo de operación.
    • Swagger/OpenAPI: documentación autogenerada y navegable.
    • Manejo centralizado de excepciones con @ControllerAdvice.
    • Tests unitarios e integración para asegurar la calidad.


Endpoints Principales

Autenticación
    • POST /usuario/registro → Crear usuario
    • POST /usuario/login → Obtener token JWT

Sucursales
    • GET /api/sucursales
    • POST /api/sucursales
    • PUT /api/sucursales/{id}
    • DELETE /api/sucursales/{id}

Productos
    • GET /api/productos
    • POST /api/productos
    • PUT /api/productos/{id}
    • PATCH /api/productos/{id}/{cantidad}
    • DELETE /api/productos/{id}

Ventas
    • POST /api/ventas
    • GET /api/ventas?sucursalId=1&fecha=2025-06-01
    • DELETE /api/ventas/{id}

Estadísticas
    • GET /api/estadisticas/producto-mas-vendido?topN=5

Seguridad
    • Todas las operaciones GET son públicas.
    • Operaciones que modifican el estado del sistema (POST, PUT, PATCH, DELETE) requieren autenticación.
    • El flujo es:
        1. Registro/Login de usuario.
        2. Obtención de token JWT.
        3. Inclusión en cabecera Authorization: Bearer <token>.


Instalación y Configuración

Requisitos
    • Java 17+
    • Maven 3.9+
    • MySQL 8+

Configuración de la base de datos
    1. Crear la base de datos:
       CREATE DATABASE gestion_ventas;
    2. Configurar variables de entorno:
       export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/gestion_ventas
       export SPRING_DATASOURCE_USERNAME=root
       export SPRING_DATASOURCE_PASSWORD=tuPassword
       export JWT_SECRET=unaClaveSecretaMuySegura123456789

Ejecución
mvn spring-boot:run

Testing
El proyecto incluye tests de integración y unitarios:
    • ProductoControllerIT
    • UsuarioControllerIT
    • SucursalControllerIT
    • VentaServiceTest
    • GestionDeVentasApplicationTests

Ejecutar con:
mvn test

Swagger UI
Disponible en:
http://localhost:8080/doc/swagger-ui.html

Ejemplo de flujo
    1. Registrar usuario:
POST /usuario/registro
{
  "myUsername": "admin",
  "myPassword": "1234"
}
    2. Login para obtener token:
POST /usuario/login
{
  "myUsername": "admin",
  "myPassword": "1234"
}
    3. Consumir endpoint protegido:
POST /api/productos
Authorization: Bearer <token>
Content-Type: application/json

{
  "nombreProducto": "Arroz",
  "precioProducto": 120.5,
  "categoriaProducto": "Alimentos",
  "cantidadProducto": 50
}

Buenas Prácticas Implementadas
    • Principio DTO vs Entity → separación entre modelo de datos y vista.
    • Validación de inputs antes de persistir en DB.
    • Manejo global de excepciones para respuestas consistentes.
    • Uso de programación funcional (Streams, Lambdas) en estadísticas.
    • Borrado lógico de productos y ventas para mantener integridad histórica.

