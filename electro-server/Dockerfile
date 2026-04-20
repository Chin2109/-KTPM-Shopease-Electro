# ===============================
# Stage 1: Build Spring Boot jar
# ===============================
FROM maven:3.9.6-eclipse-temurin-11 AS build

# Thư mục làm việc
WORKDIR /app

# Copy toàn bộ source
COPY . .

# Build (bỏ test cho nhanh)
RUN mvn clean package -DskipTests


# ===============================
# Stage 2: Run Spring Boot app
# ===============================
FROM eclipse-temurin:11-jdk-jammy

# Thư mục chạy app
WORKDIR /app

# Copy file jar từ stage build
COPY --from=build /app/target/*.jar app.jar

# Thiết lập encoding
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

# Expose port
EXPOSE 8085

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]