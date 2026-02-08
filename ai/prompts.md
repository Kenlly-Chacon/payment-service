# Registro de Prompts - Asistente IA

## Prompt 1: Configuración Inicial
**Usuario:** "Actúa como Principal Architect. Necesito migrar un servicio SOAP PaymentOrderService a REST (Spring Boot 3, Java 21, Hexagonal). Dame la estructura y pom.xml."
**Objetivo:** Establecer el esqueleto del proyecto con las mejores prácticas.

## Prompt 2: Contract-First
**Usuario:** "Genera el openapi.yaml basado en este JSON de Postman y configura el plugin maven para generar interfaces."
**Objetivo:** Alinear el contrato REST con el estándar BIAN.

## Prompt 3: Solución de Errores
**Usuario:** "Error MalformedInputException al compilar con Docker."
**Solución IA:** Se detectó conflicto de codificación (ANSI vs UTF-8) en archivos con tildes. Se forzó UTF-8 en pom.xml y Dockerfile.