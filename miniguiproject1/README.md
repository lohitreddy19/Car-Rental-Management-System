# Car Rental Management System

A Java Swing based Car Rental Management System developed using Core Java, OOP, JDBC and MySQL.

## Features

### Admin Module
- Customer Management
- Vehicle Management
- Rental Management
- Return Management
- Payment Management
- Receipt Management
- Customer Reports

### Customer Module
- Customer Login
- Browse Available Cars
- Book Vehicle
- View My Rentals
- Request Vehicle Return
- Make Payment
- View Receipts
- Update Profile
- Logout

## Technologies Used

- Java
- Core Java
- Object-Oriented Programming
- Java Swing
- JDBC
- MySQL
- SQL
- Git
- GitHub

## Java Concepts Used

- Classes and Objects
- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Collections
- Exception Handling
- File Handling
- JDBC
- CRUD Operations

## Database

The project uses MySQL as the database.

Main tables:

- users
- customer
- vehicle
- rental
- payment
- receipt

## Project Flow

Customer Login  
→ Browse Vehicles  
→ Book Vehicle  
→ My Rentals  
→ Request Return  
→ Admin Processes Return  
→ Payment  
→ Receipt

## Security

Database credentials are stored separately in `DBConfig.java`.

`DBConfig.java` is excluded from GitHub using `.gitignore`.

A sample configuration is provided in `DBConfigExample.java`.

## How to Run

1. Install Java JDK.
2. Install MySQL.
3. Create the `carrental` database.
4. Create the required tables.
5. Add your MySQL credentials to `DBConfig.java`.
6. Open the project in Eclipse.
7. Add the MySQL JDBC driver.
8. Run `Login.java`.

## Author

Sai Lohit Reddy Gayam