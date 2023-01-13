# EMPLOYEE SERVICE 

---


### TO BUILD THE APPLICATION
* mvn clean package

### TO RUN
* mvn spring-boot:run

---
## MONGO DB DOCKER

### Download mongo docker image from docker hub
* $ sudo docker pull mongo

### To start mongo
* $ docker run  -it -p 27017:27017 --name espark-mongo mongo:latest

### interact with the database through the bash shell client.
* $ docker exec -it espark-mongo bash

### create a new database
* $ mongo
* $ use employee

### save and fetch operation via mongo shell
```
$ db.employee.save({ id: 1, firstName: "firstName 1", lastName: "lastName 1", career: "career 1" })
$ db.employee.save({ id: 2, firstName: "firstName 2", lastName: "lastName 2", career: "career 2" })
$ db.employee.find({ id: 3, firstName: "firstName 3", lastName: "lastName 3", career: "career 3" })
```
---

### To Access the api via Router
* get all api
    * curl --request GET 'http://localhost:8080/router/employees'

* get address api
    * curl --request GET 'http://localhost:8080/router/employee/1'

* create address api
    * curl --location --request POST 'http://localhost:8080/router/employee' \
      --header 'Content-Type: application/json' \
      --data-raw '{"id": 4, "firstName": "firstName 4", "lastName": "lastName 4", "career": "career 4" }'

* update address api
    * curl --location --request PUT 'http://localhost:8080/router/employee/4' \
      --header 'Content-Type: application/json' \
      --data-raw '{"id": 4, "firstName": "firstName 4.0", "lastName": "lastName 4.0", "career": "career 4.0" }'

* delete address api
    *   curl --location --request DELETE 'http://localhost:8080/router/employee/3' 
