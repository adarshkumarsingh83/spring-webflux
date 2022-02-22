# springboot-sse-flux-stream-client

---

### To Build the Application 
* mvn clean package 

### To Run the Application 
* mvn spring-boor:run 

### To Access the ui page for result 
* http://localhost:8090/
### ENSURE THE SPRING BOOT WEBFLUX SERVERSIDE EVENT APPLICATION IS RUNNING


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

### To Access the flux object client
### ENSURE THE SPRING BOOT WEBFLUX SERVERSIDE EVENT APPLICATION IS RUNNING
* curl -X GET http://localhost:8090/trigger-flux-object
```
started working on webflux object stream api call
```