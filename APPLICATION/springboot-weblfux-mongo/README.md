# SPRING BOOT WEB FLUX MONGO REACTIVE 

----


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


### 
* curl -X GET 'http://localhost:8080/router/stream' -H "Accept: text/event-stream"