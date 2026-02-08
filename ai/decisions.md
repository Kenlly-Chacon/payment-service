# Decisiones de Arquitectura Asistidas por IA

1. **Arquitectura Hexagonal:** Se eligió para desacoplar el núcleo de negocio (`domain`) de la base de datos y la API REST. Esto facilita el testing y futuros cambios de tecnología.
2. **Contract-First:** Se definió primero el `openapi.yaml` para garantizar que la API cumpla estrictamente con los requisitos del cliente (BIAN) antes de escribir código Java.
3. **MapStruct:** Se seleccionó sobre ModelMapper por su rendimiento (generación de código en tiempo de compilación) y seguridad de tipos.