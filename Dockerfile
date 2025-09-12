# Stage 1: Build với Maven và JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime (chạy app với JRE/JDK 21 nhẹ hơn)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Sao chép file JAR từ stage builder
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]