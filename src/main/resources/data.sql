INSERT INTO pizzas (name, price) VALUES ('Margherita', 10.99), ('Pepperoni', 12.99), ('Vegetarian', 11.99);
INSERT INTO customers (name, address) VALUES ('John Doe', '4 New Street'), ('Artem Tartakovsky', '23 Broad Street');
INSERT INTO orders (customer_id) VALUES (1), (2);
INSERT INTO orders_pizzas (order_id, pizza_id) values (1, 1), (1, 2), (2, 2), (2, 3);
