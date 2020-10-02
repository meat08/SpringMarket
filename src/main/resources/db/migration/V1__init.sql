CREATE TABLE products (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    price   NUMERIC NOT NULL
);

create table orders (
                        id                      bigserial PRIMARY KEY,
                        price                   NUMERIC,
                        customer_name           VARCHAR(255),
                        customer_phone          BIGINT,
                        customer_address        VARCHAR(255)
);

create table order_items (
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
