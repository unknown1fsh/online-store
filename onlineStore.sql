-- Database: online_store
CREATE DATABASE IF NOT EXISTS online_store;
USE online_store;

-- Table: customer
CREATE TABLE IF NOT EXISTS customer (
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(20) DEFAULT NULL,
  address VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Table: category
CREATE TABLE IF NOT EXISTS category (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Table: product
CREATE TABLE IF NOT EXISTS product (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) DEFAULT NULL,
  price DOUBLE NOT NULL,
  stock INT NOT NULL,
  category_id BIGINT DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Table: customer_order
CREATE TABLE IF NOT EXISTS customer_order (
  id BIGINT NOT NULL AUTO_INCREMENT,
  order_date DATETIME NOT NULL,
  total DOUBLE NOT NULL,
  customer_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Table: order_item
CREATE TABLE IF NOT EXISTS order_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  quantity INT NOT NULL,
  price DOUBLE NOT NULL,
  product_id BIGINT NOT NULL,
  order_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (order_id) REFERENCES customer_order(id)
);

-- Optional: Sample Data
INSERT INTO category (name, description) VALUES 
('Electronics', 'Electronic devices and gadgets'),
('Books', 'All genres of books'),
('Clothing', 'Apparel and accessories');

INSERT INTO customer (first_name, last_name, email, phone, address) VALUES 
('John', 'Doe', 'john.doe@example.com', '1234567890', '123 Main St'),
('Jane', 'Smith', 'jane.smith@example.com', '0987654321', '456 Elm St');

INSERT INTO product (name, description, price, stock, category_id) VALUES 
('Smartphone', 'Latest model smartphone', 699.99, 50, 1),
('Novel', 'Bestselling novel', 19.99, 200, 2),
('T-shirt', 'Cotton T-shirt', 9.99, 100, 3);

-- Optional: Sample Orders
INSERT INTO customer_order (order_date, total, customer_id) VALUES
(NOW(), 1059.96, 1);

INSERT INTO order_item (quantity, price, product_id, order_id) VALUES 
(3, 299.99, 1, 1),
(1, 159.99, 2, 1);
