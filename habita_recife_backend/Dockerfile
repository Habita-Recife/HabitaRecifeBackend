FROM openjdk:21-ea-1-jdk-slim

WORKDIR /app

COPY target/habita_recife_backend-0.0.1-SNAPSHOT.jar /app/habita_recife_backend.jar

ENTRYPOINT ["java", "-jar", "habita_recife_backend.jar"]