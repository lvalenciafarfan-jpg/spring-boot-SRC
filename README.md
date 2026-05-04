# Sistema de Reservas de Canchas 🏟️

API REST para gestión de reservas de canchas deportivas,
construida con Spring Boot y PostgreSQL.

## 🛠️ Tecnologías
- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

## 🏗️ Arquitectura
El proyecto sigue arquitectura por capas:
- **Controller** → recibe requests HTTP
- **Service** → lógica de negocio
- **Repository** → acceso a datos
- **DTOs** → separación entre capas

## ▶️ Cómo correrlo localmente

1. Clona el repositorio
```bash
   git clone https://github.com/tuusuario/spring-boot-SRC.git
```
2. Configura la base de datos en `application.properties`
```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/canchas_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
```
3. Corre el proyecto desde tu IDE o con:
```bash
   mvn spring-boot:run
```

## 📋 Endpoints principales

### Reservas
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/reservas` | Listar todas |
| GET | `/api/reservas/{id}` | Buscar por ID |
| POST | `/api/reservas` | Crear reserva |
| PUT | `/api/reservas/{id}` | Actualizar |


### Usuarios
| Método | Endpoint                        | Descripción        |
|--------|---------------------------------|--------------------|
| GET    | `/api/usuarios`                 | Listar todos       |
| GET    | `/api/usuarios/{id}`            | Buscar por ID      |
| POST   | `/api/usuarios`                 | Crear usuario      |
| PUT    | `/api/usuarios/{id}/activar`    | Activar Usuario    |
| PUT    | `/api/usuarios/{id}/desactivar` | Desactivar Usuario |

### Canchas
| Método | Endpoint                           | Descripción               |
|--------|------------------------------------|---------------------------|
| GET | `/api/canchas`                     | Listar todas              |
| GET | `/api/canchas/{id}`                | Buscar por ID             |
| POST | `/api/canchas`                     | Crear cancha              |
| PUT | `/api/canchas/{id}/precio`         | Actualizar precio         |
| PUT | `/api/canchas/{id}/disponibilidad` | Actualizar disponibilidad |

## ✅ Funcionalidades implementadas
- [x] CRUD completo de Usuarios, Canchas y Reservas
- [x] Validación de conflictos de horario
- [x] Manejo global de errores (@RestControllerAdvice)
- [x] Validaciones en DTOs con @Valid (en progreso)
- [ ] Spring Security + JWT (en progreso)
- [ ] Tests unitarios (en progreso)