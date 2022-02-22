# springboot-sse-flux-stream-client

---

### To Build the Application 
* mvn clean package 

### To Run the Application 
* mvn spring-boor:run 

### To access the sse client 
### ENSURE THE SPRING BOOT WEBFLUX SERVERSIDE EVENT APPLICATION IS RUNNING
* $ curl -X GET http://localhost:8090/trigger-sse
```
  started working on sse api cal
```
### To Access the flux client 
### ENSURE THE SPRING BOOT WEBFLUX SERVERSIDE EVENT APPLICATION IS RUNNING
* curl -X GET http://localhost:8090/trigger-flux 
```
started working on webflux stream  api call
```