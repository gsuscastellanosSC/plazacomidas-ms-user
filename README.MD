# Reimportar os y volver a generar el archivo README_H2_Console.md tras el reinicio

readme_content = """# Acceso a la Consola H2 (Base de Datos en Memoria)

Este proyecto usa H2 como base de datos en memoria para desarrollo. A continuación, te explicamos cómo acceder a la consola para inspeccionar las tablas y datos cargados por Spring Boot.

---

## ✅ Requisitos
- Tener corriendo la aplicación Spring Boot (por defecto en puerto 8081)
- Asegurarse de tener esta configuración en `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update

jwt:
  secret: openssl rand -base64 32
  expiration: 86400000 # milisegundos (24 horas)


http://localhost:8081/plazacomidas-user/v1/swagger-ui/index.html#/
