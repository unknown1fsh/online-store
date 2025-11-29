# 🛍️ Online Store Management System

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)

A comprehensive e-commerce management system built with Spring Boot for managing products, orders, customers, and categories.

---

## 📋 Table of Contents / İçindekiler

- [Features / Özellikler](#features--özellikler)
- [Technology Stack / Teknoloji Yığını](#technology-stack--teknoloji-yığını)
- [Prerequisites / Gereksinimler](#prerequisites--gereksinimler)
- [Installation / Kurulum](#installation--kurulum)
- [Usage / Kullanım](#usage--kullanım)
- [API Endpoints](#api-endpoints)
- [Project Structure / Proje Yapısı](#project-structure--proje-yapısı)
- [Contributing / Katkıda Bulunma](#contributing--katkıda-bulunma)
- [License / Lisans](#license--lisans)
- [Contact / İletişim](#contact--iletişim)

---

## ✨ Features / Özellikler

### Product Management / Ürün Yönetimi
- ✅ Create, read, update, and delete products
- ✅ Manage product stock levels
- ✅ Categorize products
- ✅ Track product prices and descriptions

### Order Management / Sipariş Yönetimi
- ✅ Create customer orders with multiple items
- ✅ Automatic stock deduction on order creation
- ✅ Order total calculation
- ✅ Order history tracking

### Customer Management / Müşteri Yönetimi
- ✅ Customer registration and profile management
- ✅ Contact information storage
- ✅ Customer order history

### Category Management / Kategori Yönetimi
- ✅ Organize products into categories
- ✅ Category-based product filtering

### Additional Features / Ek Özellikler
- ✅ Modern web-based test interface
- ✅ RESTful API with comprehensive error handling
- ✅ Request validation
- ✅ CORS support for frontend integration
- ✅ Global exception handling

---

## 🛠️ Technology Stack / Teknoloji Yığını

### Backend / Arka Plan
- **Java 17** - Programming language
- **Spring Boot 3.3.2** - Application framework
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM framework
- **MySQL 8.0** - Database

### Tools & Libraries / Araçlar ve Kütüphaneler
- **MapStruct** - Object mapping
- **Lombok** - Boilerplate code reduction
- **Spring Validation** - Request validation
- **Maven** - Build tool

---

## 📦 Prerequisites / Gereksinimler

Before you begin, ensure you have the following installed:

Başlamadan önce, aşağıdakilerin yüklü olduğundan emin olun:

- **Java 17** or higher
- **Maven 3.6+**
- **MySQL Server 8.0**
- **Git** (optional)

---

## 🚀 Installation / Kurulum

### Step 1: Clone the Repository / Depoyu Klonlayın

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/online-store.git
cd online-store
```

### Step 2: Database Setup / Veritabanı Kurulumu

#### Option 1: Using the Setup Script / Script Kullanarak (Recommended / Önerilen)

**Windows:**
```bash
.\setup-database.bat
```

**Linux/Mac:**
```bash
mysql -u root -p12345 < onlineStore.sql
```

#### Option 2: Manual Setup / Manuel Kurulum

1. Start MySQL Server
2. Create database:
   ```sql
   CREATE DATABASE online_store;
   ```
3. Run the SQL script:
   ```bash
   mysql -u root -p12345 online_store < onlineStore.sql
   ```

### Step 3: Configure Database Connection / Veritabanı Bağlantısını Yapılandırın

Edit `src/main/resources/application.properties` if your MySQL credentials differ:

MySQL kimlik bilgileriniz farklıysa `src/main/resources/application.properties` dosyasını düzenleyin:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/online_store?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=12345
```

**Default Configuration / Varsayılan Yapılandırma:**
- Database: `online_store`
- Username: `root`
- Password: `12345`
- Port: `3306`

### Step 4: Build and Run / Derleme ve Çalıştırma

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

**Windows:**
```bash
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8081`

Uygulama `http://localhost:8081` adresinde başlayacaktır.

---

## 💻 Usage / Kullanım

### Web Test Interface / Web Test Arayüzü

Access the modern web interface at:
Modern web arayüzüne şu adresten erişin:

**http://localhost:8081/index.html**

This interface allows you to:
Bu arayüz şunları yapmanıza olanak tanır:

- ✅ Create and manage categories
- ✅ Create and manage products
- ✅ Create and manage customers
- ✅ Create orders with multiple items
- ✅ View all data in organized tables
- ✅ Delete records

### API Testing / API Testi

You can also test the API using:
API'yi şu şekillerde de test edebilirsiniz:

1. **Postman Collection**: Import `onlinestore.json` into Postman
2. **cURL** commands
3. **Any HTTP client**

---

## 🔌 API Endpoints

### Categories / Kategoriler

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/categories` | Get all categories |
| GET | `/api/categories/{id}` | Get category by ID |
| POST | `/api/categories` | Create new category |
| PUT | `/api/categories/{id}` | Update category |
| DELETE | `/api/categories/{id}` | Delete category |

### Products / Ürünler

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create new product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |

### Customers / Müşteriler

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{id}` | Get customer by ID |
| POST | `/api/customers` | Create new customer |
| PUT | `/api/customers/{id}` | Update customer |
| DELETE | `/api/customers/{id}` | Delete customer |

### Orders / Siparişler

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get order by ID |
| POST | `/api/orders` | Create new order |
| DELETE | `/api/orders/{id}` | Delete order |

### Order Items / Sipariş Öğeleri

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/order-items` | Get all order items |
| GET | `/api/order-items/{id}` | Get order item by ID |
| POST | `/api/order-items` | Create new order item |
| PUT | `/api/order-items/{id}` | Update order item |
| DELETE | `/api/order-items/{id}` | Delete order item |

### Example Request / Örnek İstek

**Create Product / Ürün Oluştur:**
```json
POST /api/products
Content-Type: application/json

{
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 1299.99,
  "stock": 50,
  "categoryId": 1
}
```

**Create Order / Sipariş Oluştur:**
```json
POST /api/orders
Content-Type: application/json

{
  "customerId": 1,
  "orderDate": "2024-01-15T10:30:00",
  "orderItems": [
    {
      "productId": 1,
      "quantity": 2,
      "price": 1299.99
    }
  ]
}
```

---

## 📁 Project Structure / Proje Yapısı

```
online-store/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/onlinestore/
│   │   │       ├── config/          # Configuration classes
│   │   │       ├── controller/       # REST controllers
│   │   │       ├── dto/              # Data Transfer Objects
│   │   │       ├── entity/          # JPA entities
│   │   │       ├── exception/       # Exception handling
│   │   │       ├── mapper/           # MapStruct mappers
│   │   │       ├── repository/       # Data repositories
│   │   │       └── service/          # Business logic
│   │   └── resources/
│   │       ├── application.properties # Configuration
│   │       └── static/
│   │           └── index.html        # Web test interface
│   └── test/                         # Test files
├── onlineStore.sql                   # Database schema
├── onlinestore.json                  # Postman collection
├── setup-database.bat                # Database setup script
├── pom.xml                           # Maven configuration
└── README.md                          # This file
```

### Architecture / Mimari

The project follows a **layered architecture**:

Proje **katmanlı mimari** kullanır:

1. **Controller Layer** - Handles HTTP requests
2. **Service Layer** - Contains business logic
3. **Repository Layer** - Data access
4. **DTO Layer** - Data transfer objects
5. **Entity Layer** - Database entities

---

## 🧪 Testing / Test Etme

### Using the Web Interface / Web Arayüzünü Kullanma

1. Start the application
2. Open `http://localhost:8081/index.html`
3. Use the tabs to navigate between different sections
4. Create, view, and delete records

### Using Postman / Postman Kullanma

1. Import `onlinestore.json` into Postman
2. Ensure the server is running on port 8081
3. Test all endpoints

### Using cURL / cURL Kullanma

```bash
# Get all products
curl http://localhost:8081/api/products

# Create a product
curl -X POST http://localhost:8081/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Product","price":99.99,"stock":10}'
```

---

## 🐛 Error Handling / Hata Yönetimi

The application includes comprehensive error handling:

Uygulama kapsamlı hata yönetimi içerir:

- **ResourceNotFoundException** - When a resource is not found
- **ValidationException** - When request validation fails
- **GlobalExceptionHandler** - Centralized exception handling
- **Standardized error responses** - Consistent error format

Example error response:
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Resource Not Found",
  "message": "Product not found with id: 999",
  "path": "/api/products/999"
}
```

---

## 🔒 Security Notes / Güvenlik Notları

⚠️ **Important / Önemli:**

- This is a development/demo application
- Default credentials are for local development only
- Do not use default credentials in production
- Implement proper authentication and authorization for production use

---

## 🤝 Contributing / Katkıda Bulunma

Contributions are welcome! Please follow these steps:

Katkılarınızı bekliyoruz! Lütfen şu adımları izleyin:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License / Lisans

This project is licensed under the MIT License.

Bu proje MIT Lisansı altında lisanslanmıştır.

---

## 👤 Contact / İletişim

- 🐙 GitHub: [unknown1fsh](https://github.com/unknown1fsh)

---

## 🙏 Acknowledgments / Teşekkürler

- Spring Boot team for the amazing framework
- All contributors and users of this project

---

**Made with ❤️ using Spring Boot**

**Spring Boot kullanılarak ❤️ ile yapıldı**
