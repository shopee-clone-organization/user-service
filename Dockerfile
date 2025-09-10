# Stage 1: Build a JAR file
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final, smaller image
FROM openjdk:17-jre-slim
WORKDIR /app
# Sao chép file JAR từ stage builder
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]