# Online Store Management System

Welcome to the Online Store Management System, a comprehensive solution built with Spring Boot for managing products, orders, and customers in an e-commerce environment.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

- **Product Management:** Add, update, delete, and view products with details such as name, description, price, stock, and category.
- **Order Processing:** Create and manage customer orders, track order details, and automatically update product stock levels.
- **Customer Management:** Maintain customer records, including contact information.
- **Category Management:** Organize products into categories for easy navigation and filtering.

## Architecture

The project follows a layered architecture to ensure separation of concerns and scalability. The main layers include:

- **Controller Layer:** Handles incoming HTTP requests and maps them to the appropriate service methods.
- **Service Layer:** Contains business logic and acts as an intermediary between controllers and repositories.
- **Repository Layer:** Manages data persistence and retrieval using Spring Data JPA.
- **DTO and Mapper:** Data Transfer Objects (DTOs) are used to transfer data between layers, with mappers for object conversion.

## Technology Stack

- **Backend:**
  - Java 17
  - Spring Boot
  - Spring Data JPA
  - Hibernate
  - MySQL

- **Other Tools:**
  - MapStruct (for object mapping)
  - Lombok (to reduce boilerplate code)

## Installation

To run the project locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/YOUR_GITHUB_USERNAME/online-store.git
   cd online-store
   ```

2. **Configure the Database:**
   - Ensure you have MySQL installed and running.
   - Create a database named `online_store`.
   - Update `src/main/resources/application.properties` with your MySQL credentials.

3. **Build and Run the Application:**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Access the Application:**
   - The application will be running at `http://localhost:8080`.

## Usage

- **Swagger UI:** Access the API documentation and test endpoints using Swagger UI at `http://localhost:8080/swagger-ui.html`.

- **Postman Collection:** Use the provided Postman collection to interact with the API. Import `online-store.postman_collection.json` into Postman.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/my-feature`.
3. Make your changes and commit them: `git commit -m 'Add my feature'`.
4. Push to the branch: `git push origin feature/my-feature`.
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact

For questions or feedback, please reach out to the project maintainer:

- **Name:** Selim Sercan Çınar
- **Email:** [selimsercancinar@outlook.com](mailto:selimsercancinar@outlook.com)
- **LinkedIn:** [ssercanc](https://www.linkedin.com/in/ssercanc/)
- **GitHub:** [unknown1fsh](https://github.com/unknown1fsh)
