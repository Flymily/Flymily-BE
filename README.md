# ✈ FLYMILY

![flymily-logo](https://github.com/user-attachments/assets/a784088f-bcfd-4f17-bc2d-f5624abaf697)


📝 README - Backend Flymily (Spring Boot)
📌 Descripción del Proyecto
Backend de Flymily, un buscador de viajes especializado en familias. Desarrollado con Spring Boot y PostgreSQL, proporciona APIs para gestionar:

Localidades (países, ciudades)

Transportes (tipos de transporte)

Experiencias (actividades familiares)

Usuarios (registro, autenticación)

⚙️ Configuración Inicial
Requisitos
Java 17+

Maven 3.8+

PostgreSQL 15+

Postman (para probar endpoints)

Instalación
Clona el repositorio:

bash
git clone [(https://github.com/Flymily/Flymily-BE.git)]
cd Flymily-BE
Configura la base de datos:

Crea una DB en PostgreSQL llamada flymily_db.

Configura src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flymily_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
Ejecuta la aplicación:

bash
mvn spring-boot:run
La API estará disponible en: http://localhost:8080.

🔍 Estructura del Proyecto
src/
├── main/
│   ├── java/com/flymily/
│   │   ├── controller/      # Controladores REST
│   │   ├── model/           # Entidades JPA
│   │   ├── repository/      # Repositorios
│   │   ├── service/         # Lógica de negocio
│   │   └── config/          # Configuraciones
│   └── resources/
│       ├── application.properties
│       └── data.sql         # Datos iniciales
📚 Endpoints Disponibles
Autenticación
Método	Endpoint	Descripción
POST	/api/auth/login	Login de administradores
Localidades
Método	Endpoint	Descripción
POST	/api/localidades	Crear localidad (Admin)
GET	/api/localidades	Listar todas
Transportes
Método	Endpoint	Descripción
POST	/api/transportes	Crear transporte (Admin)
Usuarios
Método	Endpoint	Descripción
POST	/api/usuarios/registro	Registrar nuevo usuario
🔧 Variables de Entorno
Configura en application.properties:

properties
# Base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/flymily_db
spring.datasource.username=postgres
spring.datasource.password=tu_password

# JWT (opcional)
jwt.secret=flymily_secret_key
🧪 Testing
Pruebas en Postman
Importa la colección de Postman (enlace al JSON).

Configura el entorno con:

base_url: http://localhost:8080

admin_token: (se genera automáticamente al hacer login)

Ejecutar Tests Unitarios
bash
mvn test
🛠️ Dependencias Principales
Spring Boot 3.1

Spring Data JPA

PostgreSQL Driver

Lombok

Spring Security (JWT)

🚀 Despliegue
Para producción:

bash
mvn clean package
java -jar target/flymily-backend-0.0.1-SNAPSHOT.jar
📄 Licencia
MIT License © 2023 [Flymily Team]


