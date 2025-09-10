# Tên Service: [Điền tên service ở đây, ví dụ: User Service]

Đây là template chuẩn cho các microservice của dự án Shopee Clone.

## Hướng dẫn sử dụng

1.  Tạo repo mới từ template này.
2.  Thay đổi artifactId trong file `pom.xml`.
3.  Chỉnh sửa các cấu hình cần thiết trong `application.yml`.

## Yêu cầu môi trường

- Java 17
- Maven 3.8+
- Docker

## Các biến môi trường cần thiết

| Tên biến      | Ví dụ        | Mô tả                  |
|---------------|--------------|------------------------|
| `DB_HOST`     | `localhost`  | Địa chỉ host của DB    |
| `DB_PORT`     | `5432`       | Cổng kết nối của DB    |
| `DB_NAME`     | `user_db`    | Tên database           |
| `DB_USER`     | `admin`      | Tên đăng nhập DB       |
| `DB_PASSWORD` | `secret`     | Mật khẩu DB            |


## Cách chạy ứng dụng

### Chạy bằng Maven
```bash
mvn spring-boot:run
```

### Chạy bằng Docker
```bash
# Build image
docker build -t shopeeclone/[tên-service] .

# Run container
docker run -p 8080:8080 -e DB_HOST=... shopeeclone/[tên-service]
```