FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/ledger-service-0.0.1-SNAPSHOT.jar ledger-service.jar

EXPOSE 8081

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","ledger-service.jar"]
