# SPRINGBOOT WEBFLUX POSTGRES REACTIVE 

----

### TO BUILD THE APPLICATION
* mvn clean package

### TO RUN
* mvn spring-boot:run

---
## POSTGRES DB DOCKER

### Download mongo docker image from docker hub
* $ sudo docker pull postgres

### To start mongo
* $  docker run --name espark-postgres -p 5432:5432 -e POSTGRES_USER=rootUser -e POSTGRES_PASSWORD=rootPwd -e POSTGRES_DB=espark  postgres

### interact with the database through the bash shell client.
* $ docker exec -it espark-postgres bash
