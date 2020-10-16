CREATE TABLE users (
    id              BIGSERIAL PRIMARY KEY,
    username        VARCHAR(30) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    email           VARCHAR(50) UNIQUE,
    phone_number    BIGINT,
    address         VARCHAR(255)
);

CREATE TABLE roles (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(50) NOT NULL
);

CREATE TABLE users_roles (
    user_id         BIGSERIAL NOT NULL,
    role_id         BIGSERIAL NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE products (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    price   NUMERIC NOT NULL
);

CREATE TABLE orders (
    id                      bigserial PRIMARY KEY,
    user_id                 BIGSERIAL REFERENCES users(id),
    price                   NUMERIC,
    recipient_name          VARCHAR(50),
    recipient_phone         BIGINT,
    recipient_address       VARCHAR(255)
);

CREATE TABLE order_items (
     id                      bigserial PRIMARY KEY,
     product_id              BIGINT REFERENCES products(id),
     order_id                BIGINT REFERENCES orders(id),
     price                   NUMERIC,
     price_per_product       NUMERIC,
     quantity                INT
);

INSERT INTO products (name, price) VALUES
('Apple', 5.40),
('Orange', 12.50),
('Bread', 5.40),
('Melon', 15.40),
('Beef', 200.00),
('Pork', 190.50),
('Chicken', 160.20),
('Coffee', 260.30),
('Tea', 180.50),
('Juice', 80.60),
('Soap-powder', 50.13),
('Soap', 22.50),
('Toothpaste', 25.40),
('Toothbrush', 15.10),
('T-shirt', 220.00),
('Shorts', 180.30),
('Pants', 240.00),
('Shoes', 260.80),
('Sneakers', 380.50),
('Pistol', 1180.20);

INSERT INTO roles (name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users (username, password, email, phone_number, address)
VALUES
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@example.com', '79991234567', 'Earth');

INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);
