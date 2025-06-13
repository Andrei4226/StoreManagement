-- Inserts for product table
INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Laptop Lenovo', 3499.99, 10, 'ELECTRONICS', 'LAP891', '2024-03-12');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Book Moara cu noroc', 89.99, 50, 'BOOKS', 'MCN2024', '2023-11-05');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Smartphone Samsung', 2799.50, 25, 'ELECTRONICS', 'SAM456', '2024-01-20');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Monitor LG', 1099.90, 15, 'ELECTRONICS', 'MON789', '2023-10-11');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Book Ion', 119.00, 40, 'BOOKS', 'BOOK251', '2024-02-01');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Mouse Logitech', 149.90, 100, 'ELECTRONICS', 'LOG321', '2024-04-07');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Headphones JBL', 259.00, 30, 'ELECTRONICS', 'JBL192', '2023-12-25');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Book Atomic Habits', 100.00, 45, 'BOOKS', 'BOOK910', '2024-05-13');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Boxe SONY', 499.00, 12, 'ELECTRONICS', 'SONY142', '2023-09-14');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Course Development', 129.90, 60, 'OTHER', 'CODE324', '2024-06-01');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Lavazza Coffee', 45.99, 100, 'GROCERIES', 'COF928', '2024-06-01');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Barilla Pasta', 12.49, 200, 'GROCERIES', 'BARP009', '2024-05-15');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Olive Oil', 29.99, 80, 'GROCERIES', 'OILO008', '2024-06-08');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('White T-Shirt', 59.90, 50, 'CLOTHING', 'TSRT329', '2024-03-22');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Black Cap', 299.00, 20, 'CLOTHING', 'CAPB335', '2023-11-10');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Lego Audi Car', 127.65, 15, 'TOYS', 'LEGO991', '2024-01-01');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Lego Excavator', 89.50, 60, 'TOYS', 'LEGO334', '2024-03-05');

INSERT INTO product (name, price, stocks, category, code, release_date)
VALUES ('Decorative Vase', 59.50, 100, 'OTHER', 'VASE349', '2024-05-12');

-- Inserts for products_tags table
INSERT INTO product_tags (product_id, tag) VALUES (1, 'tech');
INSERT INTO product_tags (product_id, tag) VALUES (1, 'lenovo');
INSERT INTO product_tags (product_id, tag) VALUES (1, 'laptop');

INSERT INTO product_tags (product_id, tag) VALUES (2, 'book');
INSERT INTO product_tags (product_id, tag) VALUES (2, 'school');

INSERT INTO product_tags (product_id, tag) VALUES (3, 'android');
INSERT INTO product_tags (product_id, tag) VALUES (3, 'smartphone');

INSERT INTO product_tags (product_id, tag) VALUES (4, 'display');
INSERT INTO product_tags (product_id, tag) VALUES (4, 'LG');

INSERT INTO product_tags (product_id, tag) VALUES (5, 'book');
INSERT INTO product_tags (product_id, tag) VALUES (5, 'school');