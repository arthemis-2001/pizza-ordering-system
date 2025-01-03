# Pizza Ordering Management System

A REST API application for managing a pizza ordering system using Spring Boot framework and PostgreSQL database.

## Entities Included

- Customer - attributes *name*, *address*, *orders*.
- Order - attributes *createdAt*, *customer*, *pizzaOrders*.
- Pizza - attributes *name*, *price*.
- PizzaOrder - attributes *order*, *pizza*, *quantity*.

## Tech Stack

- Spring Boot
- Spring Data JPA
- PostgreSQL
- jUnit
- Maven

## Installation

### Prerequisites

Ensure that you have the following installed on your machine:

- Java 17+
- Git
- PostgreSQL 16.3+
- Maven 3.9+

### Steps

- Clone this repo to your local machine.
- Run `mvn clean install` to run tests and package the project into a JAR file.
- Run `java -jar pizza-ordering-system-0.0.1-SNAPSHOT.jar` to start the app.
- When the app is started, it executes SQL commands from the file data.sql.

### Database Setup

For the app to work properly, you must create a database in PostgreSQL and also specify the following configuration parameters in `application.properties` file:

```
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

## Project Structure
```text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── artemtartakovsky/
│   │           └── pizza_ordering_system/
│   │               ├── controller/ - Rest Controllers for respective services
│   │               ├── dto/ - Data Transfer Objects for models
│   │               ├── model/ - JPA entities
│   │               ├── repository/ - JPA repositories for models
│   │               └── service/ - Spring Framework Services for models
│   └── resources/
│       ├── application.properties
│       └── data.sql - SQL script executed at the start of the app
└── test/
    └── java/
        └── com/
            └── artemtartakovsky/
                └── pizza_ordering_system/
                    └── Tests for all controllers
```
## API Endpoints

This app provides the following API endpoints:

| HTTP Method | Endpoint              | Description                                                                                  |
| ----------- | --------------------- | -------------------------------------------------------------------------------------------- |
| GET         | `/api/pizzas`         | Returns a list of all pizzas.                                                                |
| GET         | `/api/pizzas/{id}`    | Returns a pizza by its id.                                                                   |
| POST        | `/api/pizzas`         | Takes a JSON request body and creates a pizza. Fields *name* and *price* are mandatory.      |
| DELETE      | `/api/pizzas/{id}`    | Deletes a pizza by its id.                                                                   |
| GET         | `/api/customers       | Returns a list of all customers.                                                             |
| GET         | `/api/customers/{id}` | Returns a customer by his id.                                                                |
| POST        | `/api/customers`      | Takes a JSON request body and creates a customer. Fields *name* and *address* are mandatory. |
| DELETE      | `/api/customers/{id}` | Deletes a customer by its id.                                                                |
| GET         | `/api/orders`         | Returns a list of all orders.                                                                |
| GET         | `/api/orders/{id}`    | Returns an order by its id.                                                                  |
| POST        | `/api/orders`         | Takes a JSON request body and creates an order. Field *customerId* is mandatory, *pizzaOrders* is optional and consists of arrays, each with pizzaId and quantity attributes. |

## License
This project is licensed under the MIT License - see the LICENSE file for details.

