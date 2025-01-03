DELETE FROM pizzas;
DELETE FROM customers;
INSERT INTO pizzas (name, price) VALUES ('Margherita', 10.99), ('Pepperoni', 12.99), ('Vegetarian', 11.99);
INSERT INTO customers (name, address) VALUES ('John Doe', '4 New Street'), ('Artem Tartakovsky', '23 Broad Street');
