# Petshop

### About
Demo of a multi-module Spring Boot project using layered architecture.<br/>
Implementation of a very simple e-shop application selling pet products with REST API.<br/>

Technologies used: 
- [x] Docker
- [x] PostgreSQL
- [x] Liquibase
- [x] Swagger
- [x] JWT

### API


## User Guide

There is one `admin` user.


## Developer's Guide

### Prerequisities

You need have to installed the following technologies and products on your machine:
- [ ] JAVA JDK 11+
- [ ] Docker
- [ ] Git

### Local Development

1. Check-out the project from the GitHub repository: https://github.com/Jo2o/petshop
1. Import the project into your IDE.
1. Run the [docker-compose](docker/docker-compose.yml) file. This will start the PostgreSQL DB.
1. Build and run the project in your IDE.

> Note 1: This project uses Liquibase to initialize the DB schema; therefore, you don't need to run any SQL scripts.
