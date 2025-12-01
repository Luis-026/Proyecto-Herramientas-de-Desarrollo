# Etapa 1: compilar el proyecto con Maven (mvnw)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiar archivos necesarios para resolver dependencias y compilar
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

# Dar permisos de ejecución al wrapper y compilar
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: imagen final para ejecutar la app
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar el JAR generado desde la etapa de build
COPY --from=build /app/target/herramientas-1.2-version.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]