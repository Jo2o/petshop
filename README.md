# Petshop

### Overview
Demo of a multi-module Maven Spring Boot project using layered architecture.<br/>
Implementation of a very simple e-shop application selling pet products with REST API with JWT auth.<br/>

Technologies used: 
- [x] Docker
- [x] PostgreSQL
- [x] Liquibase
- [x] Swagger
- [x] JWT
- [x] Maven

## User Guide

This back-end application uses Swagger which provides a very convenient UI to work with REST endpoints.<br/>
The application is deployed on Heroku and you can access its Swagger via this link: [jo2o-petshop](http://jo2o-petshop.herokuapp.com/swagger-ui.html#/) <br/>

> **Note**: When application deployed on Heroku is idle for longer period, Heroku shuts it down.<br/>Therefore, for the first time you may wait longer 
> until the application starts up.

Public endpoints are accessible without authentication:
- /v1/auth/login
- /v1/auth/register
- /v1/products/public-products
- /v1/products/public-products/{id}

#### Authorization

You are able to provide the JWT token which is returned to you after a successful login to Swagger. You need to click `Authorize` button 
and paste it to the popup window. Then you don't need to worry about providing the JWT token with each request. Of course until the session expires.

These are the test users that were already created:

|USERNAME|PASSWORD|
|--------|--------|
|karol|karol|
|jana|jana|
|zuza|zuza|
|juro|juro|

> Note: Admin user is created as well. Will be provided on request :)

After you login you are able to access the following endpoints which provide data only for currently logged-in user:
- /v1/orders/user-orders
- /v1/orders/user-orders/crete-order

## Developer's Guide

The project follows standard technologies used in Spring Boot/Java development. Therefore there should not be any surprises. :)

### Prerequisities

You need to install the following technologies and products on your machine:
- [ ] JAVA JDK 11+
- [ ] Docker
- [ ] Git
- [ ] Maven

### Local Development

1. Check-out the project from the GitHub repository: https://github.com/Jo2o/petshop
1. Import the project into your IDE.
1. Run the [docker-compose](docker/docker-compose.yml) file. This will provide running PostgreSQL DB.
1. Build and run the project in your IDE.

> Note: This project uses Liquibase to initialize the DB schema; therefore, you don't need to run any SQL scripts.

### Testing

The project uses: JUnit5, Mockito and AssertJ.

Unit tests:
- OrderServiceTest
- PriceMapperTest

Integration tests:
- GetPublicProductsIT



