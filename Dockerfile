FROM openjdk:17-alpine
COPY ./target/api-producao-1.0.jar /app/api-producao-1.0.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "api-producao-1.0.jar"]
