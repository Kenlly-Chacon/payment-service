# Payment Initiation Microservice (BIAN)

Microservicio REST para la gestión de órdenes de pago, basado en el dominio de servicio BIAN (Payment Initiation) y desarrollado siguiendo una arquitectura hexagonal (Ports & Adapters).

## 📋 Descripción
Este servicio permite:
- Iniciar una orden de pago (**POST**).
- Consultar el detalle de una orden (**GET**).
- Consultar el estado de una orden (**GET**).

La solución reemplaza el legado SOAP por una arquitectura moderna Cloud-Native utilizando **Contract-First**.

## 🛠 Stack Tecnológico
- **Lenguaje:** Java 21
- **Framework:** Spring Boot 3.5.10
- **Base de Datos:** PostgreSQL 15
- **API Definition:** OpenAPI 3.0 (Contract-First)
- **Mapping:** MapStruct
- **Containerización:** Docker & Docker Compose

## 🚀 Ejecución

### Requisitos Previos
- Docker y Docker Compose instalados.
- Java 21 (Opcional si se usa Docker).

### Levantar el entorno (Recomendado)
El proyecto incluye un `docker-compose` que orquesta la base de datos y la aplicación.

```bash
docker-compose up --build