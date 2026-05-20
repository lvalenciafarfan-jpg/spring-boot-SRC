# Sistema de Reservas de Canchas 🏟️
API REST para gestión de reservas de canchas deportivas, construida con Spring Boot y PostgreSQL.

## 🛠️ Tecnologías
- Java 21
- Spring Boot
- Spring Security + JWT
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Lombok

## 🏗️ Arquitectura
El proyecto sigue arquitectura por capas:
- **Controller** → recibe requests HTTP
- **Service** → lógica de negocio
- **Repository** → acceso a datos
- **DTOs** → separación entre capas

## ▶️ Cómo correrlo localmente

1. Clona el repositorio
```bash
git clone https://github.com/lvalenciafarfan-jpg/spring-boot-SRC.git
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

## 📋 Endpoints

### Auth
| Método | Endpoint       | Descripción                |
|--------|----------------|----------------------------|
| POST   | /auth/registro | Registrar nuevo usuario    |
| POST   | /auth/login    | Login — devuelve token JWT |

### Usuarios
| Método | Endpoint               | Descripción        |
|--------|------------------------|--------------------|
| GET    | /usuarios              | Listar todos       |
| GET    | /usuarios/{id}         | Buscar por ID      |
| PUT    | /usuarios/{id}/activar | Activar usuario    |
| PUT    | /usuarios/{id}/desactivar | Desactivar usuario |

### Canchas
| Método | Endpoint                     | Descripción            |
|--------|------------------------------|------------------------|
| GET    | /canchas                     | Listar todas           |
| GET    | /canchas/{id}                | Buscar por ID          |
| POST   | /canchas                     | Crear cancha           |
| PUT    | /canchas/{id}/precio         | Actualizar precio      |
| PUT    | /canchas/{id}/disponibilidad | Cambiar disponibilidad |

### Reservas
| Método | Endpoint                | Descripción      |
|--------|-------------------------|------------------|
| GET    | /reservas               | Listar todas     |
| GET    | /reservas/{id}          | Buscar por ID    |
| POST   | /reservas               | Crear reserva    |
| PUT    | /reservas/{id}/cancelar | Cancelar reserva |

## ✅ Funcionalidades implementadas
- [x] CRUD de Usuarios, Canchas y Reservas
- [x] Autenticación y autorización con Spring Security + JWT
- [x] Validación de conflictos de horario en reservas
- [x] Manejo global de errores (@RestControllerAdvice)
- [x] Validaciones en DTOs con @Valid
- [ ] Tests unitarios (en progreso)