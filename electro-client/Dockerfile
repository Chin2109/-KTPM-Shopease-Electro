# Stage 1: Build React app
FROM node:18-alpine AS build
WORKDIR /app

# Copy toàn bộ source code vào container
COPY . .

# Cài dependencies và build project
RUN npm install && npm run build


# Stage 2: Serve with Nginx
FROM nginx:1.25-alpine

# Copy file cấu hình nginx
COPY nginx.conf /etc/nginx/nginx.conf

# Copy file build từ stage 1 sang
WORKDIR /usr/share/nginx/html
COPY --from=build /app/build .

EXPOSE 80

# Chạy nginx
CMD ["nginx", "-g", "daemon off;"]
