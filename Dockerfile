# Etapa 1: Build
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

# Etapa 2: Runtime
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/api-producao-1.0.jar /app/api-producao-1.0.jar
ENTRYPOINT ["java", "-jar", "api-producao-1.0.jar"]
