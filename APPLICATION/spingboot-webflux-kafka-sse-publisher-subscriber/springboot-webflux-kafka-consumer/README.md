# springboot-webflux-kafka-consumer

----

### To Build the Code
* mvn clean package

### To Run the Application
* mvn spring-boot:run 

---

# WebFlux streams 
### To Hit the api via controller
* curl -X GET http://localhost:8080/controller/stream -H "Accept:text/event-stream"

### To Hit the api via router
* curl -X GET http://localhost:8080/router/stream -H "Accept:text/event-stream"

---

## Server Side Events 
### To Hit the api via controller
* curl -X GET http://localhost:8080/controller/sse -H "Accept:text/event-stream"

### To Hit the api via router
* * curl -X GET http://localhost:8080/router/sse -H "Accept:text/event-stream"
