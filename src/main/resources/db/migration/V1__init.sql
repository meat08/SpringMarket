CREATE TABLE users (
    id              BIGSERIAL PRIMARY KEY,
    username        VARCHAR(30) NOT NULL,
    password        VARCHAR(255) NOT NULL
);

CREATE TABLE user_profiles (
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGSERIAL NOT NULL,
    first_name      VARCHAR(30) NOT NULL,
    last_name       VARCHAR(30) NOT NULL,
    email           VARCHAR(50) UNIQUE,
    birthday        INT NOT NULL,
    phone_number    BIGINT,
    address         VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
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

CREATE TABLE categories (
      id      BIGSERIAL PRIMARY KEY,
      title    VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    id          BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    price       NUMERIC NOT NULL,
    category_id BIGSERIAL NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
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

INSERT INTO categories (id, title) VALUES
(1, 'Фрукты'),
(2, 'Мясо'),
(3, 'Напитки'),
(4, 'Гигиена'),
(5, 'Одежда'),
(6, 'Оружие');

INSERT INTO products (title, price, category_id) VALUES
('Яблоко', 5.40, 1),
('Апельсин', 12.50, 1),
('Хлеб', 5.40, 1),
('Арбуз', 15.40, 1),
('Говядина', 200.00, 2),
('Свинина', 190.50, 2),
('Курица', 160.20, 2),
('Кофе', 260.30, 3),
('Чай', 180.50, 3),
('Сок', 80.60, 3),
('Стиральный порошок', 50.13, 4),
('Мыло', 22.50, 4),
('Зубная паста', 25.40, 4),
('Зубная щетка', 15.10, 4),
('Футболка', 220.00, 5),
('Шорты', 180.30, 5),
('Штаны', 240.00, 5),
('Туфли', 260.80, 5),
('Кроссовки', 380.50, 5),
('BFG', 1180.20, 6);

INSERT INTO roles (name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO users (username, password)
VALUES
('user1', '$2a$10$KTAfCx9c84nYy010lfkX8.lYpzojGKBCotQgBuT0vIqS81uuUNn5S');

INSERT INTO user_profiles (user_id, first_name, last_name, email, birthday, phone_number, address)
VALUES
(1, 'John', 'Wick', 'jw@continental.com', 1990, 17771234567, 'Continental');

INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);
