-- Inserts for supplier table
INSERT INTO supplier (name, contact) VALUES ('Emag', 'contact@lenovo.com');
INSERT INTO supplier (name, contact) VALUES ('Elefant', 'contact@elefant.ro');
INSERT INTO supplier (name, contact) VALUES ('Samsung', 'contact@samsung.ro');
INSERT INTO supplier (name, contact) VALUES ('LG', 'contact@lg.ro');
INSERT INTO supplier (name, contact) VALUES ('Editura Eminescu', 'contact@eminescu.ro');

-- Inserts for product table
INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Laptop Lenovo', 3499.99, 10, 'ELECTRONICS', 'LAP891', '2024-03-12', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Book Moara cu noroc', 89.99, 50, 'BOOKS', 'MCN2024', '2023-11-05', 5);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Smartphone Samsung', 2799.50, 25, 'ELECTRONICS', 'SAM456', '2024-01-20', 3);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Monitor LG', 1099.90, 15, 'ELECTRONICS', 'MON789', '2023-10-11', 4);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Book Ion', 119.00, 40, 'BOOKS', 'BOOK251', '2024-02-01', 5);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Mouse Logitech', 149.90, 100, 'ELECTRONICS', 'LOG321', '2024-04-07', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Headphones JBL', 259.00, 30, 'ELECTRONICS', 'JBL192', '2023-12-25', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Book Atomic Habits', 100.00, 45, 'BOOKS', 'BOOK910', '2024-05-13', 5);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Boxe SONY', 499.00, 12, 'ELECTRONICS', 'SONY142', '2023-09-14', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Course Development', 129.90, 60, 'OTHER', 'CODE324', '2024-06-01', 3);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Lavazza Coffee', 45.99, 100, 'GROCERIES', 'COF928', '2024-06-01', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Barilla Pasta', 12.49, 200, 'GROCERIES', 'BARP009', '2024-05-15', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Olive Oil', 29.99, 80, 'GROCERIES', 'OILO008', '2024-06-08', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('White T-Shirt', 59.90, 50, 'CLOTHING', 'TSRT329', '2024-03-22', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Black Cap', 299.00, 20, 'CLOTHING', 'CAPB335', '2023-11-10', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Lego Audi Car', 127.65, 15, 'TOYS', 'LEGO991', '2024-01-01', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Lego Excavator', 89.50, 60, 'TOYS', 'LEGO334', '2024-03-05', 1);

INSERT INTO product (name, price, stocks, category, code, release_date, supplier_id)
VALUES ('Decorative Vase', 59.50, 100, 'OTHER', 'VASE349', '2024-05-12', 1);

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