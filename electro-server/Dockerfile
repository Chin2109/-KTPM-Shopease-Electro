
#Stage 1: Build Spring Boot jar
FROM maven:3.9.6-eclipse-temurin-11 AS build

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép toàn bộ source code vào container
COPY . .

# Build project (bỏ qua test để build nhanh hơn)
RUN mvn clean package -DskipTests


# 🚀 Stage 2: Run Spring Boot app
FROM eclipse-temurin:11-jdk-jammy

# Cài đặt gói phụ thuộc tối thiểu
RUN apt-get update \
    && apt-get install -y --no-install-recommends netcat locales \
    && apt-get clean \
    && locale-gen C.UTF-8

# Đặt thư mục làm việc mặc định
WORKDIR /electro-server

# Sao chép file .jar đã build từ stage 1 sang
COPY --from=build /app/target/electro-0.0.1-SNAPSHOT.jar /electro-server/

# Thiết lập ngôn ngữ và locale
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

# Mở port cho ứng dụng Spring Boot
EXPOSE 8085

# Lệnh khởi chạy ứng dụng
ENTRYPOINT ["java", "-jar", "electro-0.0.1-SNAPSHOT.jar"]
