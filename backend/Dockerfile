# BUILD Application
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Build .jar without tests
COPY src ./src
RUN mvn clean package -DskipTests

# RUN & START Application
FROM amazoncorretto:17-alpine
WORKDIR /app

# CURL
RUN apk add --no-cache curl

# Copy original as app.jar for start
COPY --from=builder /app/target/MoodMap-REST-API.jar /app/app.jar

# Javalin
EXPOSE 7070

# Start REST API
ENTRYPOINT ["java", "-Dset.env=production", "-jar", "/app/app.jar"]