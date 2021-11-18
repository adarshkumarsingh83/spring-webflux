# SPRING BOOT WEB FLUX MONGODB CRUD OPERATIONS 
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
* $ use espark

### save and fetch operation via mongo shell 
$ db.employee.save({ name: "adarsh",email:"adarsh@kumar" })
$ db.employee.save({ name: "radha",email:"radha@singh" })
$ db.employee.find({ name: "adarsh" })
---

### To Access the api via Router
* get all api
    * curl --request GET 'http://localhost:8080/router/employees'

* get employee api
    * curl --request GET 'http://localhost:8080/router/employee/0'

* create employee api
    * curl --location --request POST 'http://localhost:8080/router/employee' \
      --header 'Content-Type: application/json' \
      --data-raw '{"id": 100,"name": "employee_100" }'
* update employee api
    * curl --location --request PUT 'http://localhost:8080/router/employee/100' \
      --header 'Content-Type: application/json' \
      --data-raw '{"id": 100,"name": "employee_1001"}'

* delete employee api
    *   curl --location --request DELETE 'http://localhost:8080/router/employee/100' 
