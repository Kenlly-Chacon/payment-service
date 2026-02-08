# ETAPA 1: Construcción (Build)
# Usamos una imagen con Maven y JDK 21 para compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Empaquetamos saltando tests para agilizar el build en docker
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Runtime)
# Usamos una imagen ligera (Alpine) solo con JRE 21 para correr la app
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos solo el JAR generado en la etapa anterior
COPY --from=builder /app/target/payment-service-1.0.0.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]