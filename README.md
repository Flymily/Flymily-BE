# ✈ FLYMILY

![flymily-logo](https://github.com/user-attachments/assets/a784088f-bcfd-4f17-bc2d-f5624abaf697)

# ✈️ Flymily Backend

Flymily es el backend de una aplicación web para una agencia de viajes. Incluye autenticación para administradoras y un panel de administración con funcionalidades completas de gestión de viajes y artículos de comunidad.

## 📦 Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3+**
- **Spring Security + BCrypt**
- **JPA / Hibernate**
- **PostgreSQL**
- **Dotenv (Gestor de variables de entorno)**
- **Maven 3.8+**
- **Lombok**
- **MapStruct (DTO mapping)**
- **CORS habilitado para frontend**

## 📂 Estructura del proyecto

El proyecto sigue una arquitectura en capas estándar (controller - service - repository), con una estructura clara y modularizada. Incluye controladores REST, servicios de negocio, repositorios JPA, entidades, DTOs y excepciones personalizadas.

C:.
│   .env
│   .gitattributes
│   .gitignore
│   mvnw
│   mvnw.cmd
│   pom.xml
│   README.md
│
├───.mvn
│   └───wrapper
│           maven-wrapper.properties
│
├───.vscode
│       settings.json
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───flymily
│   │   │           └───flymily
│   │   │               │   FlymilyBeApplication.java
│   │   │               │
│   │   │               ├───config
│   │   │               │       SecurityConfig.java
│   │   │               │       WebConfig.java
│   │   │               │
│   │   │               ├───controller
│   │   │               │       AgenciaController.java
│   │   │               │       LocalidadController.java
│   │   │               │       LoginController.java
│   │   │               │       PostsComunidadController.java
│   │   │               │       TipoViajeController.java
│   │   │               │       TransporteController.java
│   │   │               │       UsuarioController.java
│   │   │               │       ViajeController.java
│   │   │               │
│   │   │               ├───datainit
│   │   │               │       EdadRangoDataLoader.java
│   │   │               │
│   │   │               ├───dto
│   │   │               │       AgenciaDTO.java
│   │   │               │       CreateViajeRequestDTO.java
│   │   │               │       LoginDTO.java
│   │   │               │       PostsComunidadDTO.java
│   │   │               │       RegisterDTO.java
│   │   │               │       TipoViajeDTO.java
│   │   │               │       TransporteDTO.java
│   │   │               │       ViajeDetalleDTO.java
│   │   │               │       ViajeFilterDTO.java
│   │   │               │       ViajeSencilloDTO.java
│   │   │               │
│   │   │               ├───exceptions
│   │   │               │       DuplicateResourceException.java
│   │   │               │       EdadRangoNotFoundException.java
│   │   │               │       GlobalExceptionHandler.java
│   │   │               │       InvalidCredentialsException.java
│   │   │               │       InvalidFilterException.java
│   │   │               │       LocalidadNotFoundException.java
│   │   │               │       ResourceNotFoundException.java
│   │   │               │       TipoViajeIdNotFoundException.java
│   │   │               │       TipoViajeNotFoundException.java
│   │   │               │       TituloYaExisteException.java
│   │   │               │       UserAlreadyExistsException.java
│   │   │               │
│   │   │               ├───mapper
│   │   │               │       ViajeMapper.java
│   │   │               │
│   │   │               ├───model
│   │   │               │       Agencia.java
│   │   │               │       EdadRango.java
│   │   │               │       Localidad.java
│   │   │               │       PostsComunidad.java
│   │   │               │       TipoViaje.java
│   │   │               │       Transporte.java
│   │   │               │       Usuario.java
│   │   │               │       Viaje.java
│   │   │               │
│   │   │               ├───repository
│   │   │               │       AgenciaRepository.java
│   │   │               │       EdadRangoRepository.java
│   │   │               │       LocalidadRepository.java
│   │   │               │       PostsComunidadRepository.java
│   │   │               │       TipoViajeRepository.java
│   │   │               │       TransporteRepository.java
│   │   │               │       UsuarioRepository.java
│   │   │               │       ViajeRepository.java
│   │   │               │
│   │   │               └───service
│   │   │                       AgenciaService.java
│   │   │                       EdadRangoService.java
│   │   │                       LocalidadService.java
│   │   │                       PostsComunidadService.java
│   │   │                       TipoViajeService.java
│   │   │                       TransporteService.java
│   │   │                       UsuarioService.java
│   │   │                       ViajeService.java
│   │   │
│   │   └───resources
│   │           application.properties
│   │
│   └───test
│       └───java
│           └───com
│               └───flymily
│                   └───Flymily_BE
│                           FlymilyBeApplicationTests.java
│
└───target
    ├───classes
    │   │   application.properties
    │   │
    │   └───com
    │       └───flymily
    │           └───flymily
    │               │   FlymilyBeApplication.class
    │               │
    │               ├───config
    │               │       SecurityConfig.class
    │               │       WebConfig$1.class
    │               │       WebConfig.class
    │               │
    │               ├───controller
    │               │       AgenciaController.class
    │               │       LocalidadController.class
    │               │       LoginController.class
    │               │       PostsComunidadController.class
    │               │       TipoViajeController.class
    │               │       TransporteController.class
    │               │       UsuarioController.class
    │               │       ViajeController.class
    │               │
    │               ├───datainit
    │               │       EdadRangoDataLoader.class
    │               │
    │               ├───dto
    │               │       AgenciaDTO.class
    │               │       CreateViajeRequestDTO.class
    │               │       LoginDTO.class
    │               │       PostsComunidadDTO.class
    │               │       RegisterDTO.class
    │               │       TipoViajeDTO.class
    │               │       TransporteDTO.class
    │               │       ViajeDetalleDTO.class
    │               │       ViajeFilterDTO.class
    │               │       ViajeSencilloDTO.class
    │               │
    │               ├───exceptions
    │               │       DuplicateResourceException.class
    │               │       EdadRangoNotFoundException.class
    │               │       GlobalExceptionHandler.class
    │               │       InvalidCredentialsException.class
    │               │       InvalidFilterException.class
    │               │       LocalidadNotFoundException.class
    │               │       ResourceNotFoundException.class
    │               │       TipoViajeIdNotFoundException.class
    │               │       TipoViajeNotFoundException.class
    │               │       TituloYaExisteException.class
    │               │       UserAlreadyExistsException.class
    │               │
    │               ├───mapper
    │               │       ViajeMapper.class
    │               │
    │               ├───model
    │               │       Agencia.class
    │               │       EdadRango.class
    │               │       Localidad.class
    │               │       PostsComunidad.class
    │               │       TipoViaje.class
    │               │       Transporte.class
    │               │       Usuario.class
    │               │       Viaje.class
    │               │
    │               ├───repository
    │               │       AgenciaRepository.class
    │               │       EdadRangoRepository.class
    │               │       LocalidadRepository.class
    │               │       PostsComunidadRepository.class
    │               │       TipoViajeRepository.class
    │               │       TransporteRepository.class
    │               │       UsuarioRepository.class
    │               │       ViajeRepository.class
    │               │
    │               └───service
    │                       AgenciaService.class
    │                       EdadRangoService$DataLoader.class
    │                       EdadRangoService.class
    │                       LocalidadService.class
    │                       PostsComunidadService.class
    │                       TipoViajeService.class
    │                       TransporteService.class
    │                       UsuarioService.class
    │                       ViajeService.class
    │
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    └───test-classes
        └───com
            └───flymily
                └───Flymily_BE
                        FlymilyBeApplicationTests.class

## 🔐 Autenticación de Administradoras

El sistema de autenticación implementado está diseñado exclusivamente para el acceso de administradoras al panel de gestión:

- ✅ **Acceso restringido**: Solo las cuentas de administradoras pueden iniciar sesión.
- 🔒 **Contraseñas protegidas**: Las contraseñas se almacenan con hash BCrypt.
- 🧹 **Validación segura**: Verificación hash-contraseña en cada login.
- 🔐 **Sesiones tradicionales**: No se utiliza JWT; la autenticación se maneja con sesión de backend.

## ✍️ Funcionalidades principales

### 🔑 Autenticación (`/auth`)

- `POST /auth/register` - Registro de nueva administradora.
- `POST /auth/login` - Login de administradora.

### 🌍 Viajes (`/api/viajes`)

- `GET /api/viajes` - Listar todos los viajes (público).
- `POST /api/viajes` - Crear viaje (admin).
- `PUT /api/viajes/{id}` - Editar viaje (admin).
- `DELETE /api/viajes/{id}` - Eliminar viaje (admin).

### 📰 Artículos Comunidad (`/api/articulos`)

- `GET /api/articulos` - Listar todos los artículos (público).
- `POST /api/articulos` - Crear artículo (admin).
- `PUT /api/articulos/{id}` - Editar artículo (admin).
- `DELETE /api/articulos/{id}` - Eliminar artículo (admin).

---

## 🧪 Pruebas con Postman

Puedes utilizar [Postman](https://www.postman.com/) para probar la API:

1. Autentícate como administradora (POST /auth/login).
2. Usa la cookie de sesión generada para acceder a rutas protegidas.
3. Prueba operaciones CRUD para viajes y artículos.

---

## 🚀 Instalación y puesta en marcha

### 📦 Requisitos previos

- Java 21+
- Maven 3.8+
- PostgreSQL en funcionamiento
- IDE como IntelliJ o Visual Studio Code

### 📂 Clonar el repositorio

```bash
git clone https://github.com/Flymily/Flymily-BE.git
cd Flymily-BE
```

### ⚙️ Configuración del archivo `.env`

Este proyecto utiliza **dotenv-java**. Crea un archivo `.env` en la raíz con este contenido:

```env
DB_URL=jdbc:postgresql://localhost:5432/Flymily-DB
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña
```

Asegúrate de que el archivo `.env` esté listado en `.gitignore`.

### ▶️ Ejecución del proyecto

1. Abre el proyecto en tu IDE.
2. Ejecuta la clase `FlymilyBeApplication.java`.
3. El backend estará disponible en: [http://localhost:8080](http://localhost:8080)

---

## 📃 Documentación de la API

### 🔐 Autenticación de administradoras

| Método | Endpoint       | Descripción                      | Protegido |
|--------|----------------|----------------------------------|-----------|
| POST   | /auth/login    | Login de administradora          | No        |

### ✈️ Gestión de viajes

| Método | Endpoint             | Descripción                      | Protegido |
|--------|----------------------|----------------------------------|-----------|
| GET    | /admin/travels       | Listar viajes                    | Sí        |
| POST   | /admin/travels       | Crear viaje                      | Sí        |
| PUT    | /admin/travels/{id}  | Editar viaje                     | Sí        |
| DELETE | /admin/travels/{id}  | Eliminar viaje                   | Sí        |

### 📝 Gestión de artículos

| Método | Endpoint                | Descripción                   | Protegido |
|--------|-------------------------|-------------------------------|-----------|
| GET    | /admin/articles         | Listar artículos              | Sí        |
| POST   | /admin/articles         | Crear artículo                | Sí        |
| PUT    | /admin/articles/{id}    | Editar artículo               | Sí        |
| DELETE | /admin/articles/{id}    | Eliminar artículo             | Sí        |

---

## 🤝 Equipo de desarrollo

| Nombre completo        | Perfil de LinkedIn                               |
|------------------------|--------------------------------------------------|
| Priscila Guillén       | (https://linkedin.com/in/priscilaguillen)        |
| Guadalupe Hani         | (https://linkedin.com/in/guadalupe-hani)         |
| Miriam Sánchez         | (https://linkedin.com/in/miriam-sanchez-ordoñez) |
| Israel Espin           | (https://linkedin.com/in/israelespin)            |

---

## 📚 Licencia

Este proyecto está licenciado bajo la **MIT License**. Consulta el archivo `LICENSE` para más información.

---

Gracias por visitar el repositorio de **Flymily**. Para cualquier consulta o colaboración, no dudes en contactarnos a través de nuestros perfiles de LinkedIn.
