FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/habita_recife_backend-0.0.1-SNAPSHOT.jar habita_recife_backend.jar
ENTRYPOINT ["java", "-jar", "habita_recife_backend.jar"]
