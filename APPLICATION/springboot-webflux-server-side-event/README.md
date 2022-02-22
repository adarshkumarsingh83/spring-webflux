# springboot-webflux-server-side-event

---

### To Build the Code 
* mvn clean package 

### To run the code 
* mvn spring-boot:run 

### To access the flux endpoint 
* curl -X GET http://localhost:8080/router/data-stream-flux
```    
data: Welcome to Espark from Flux -> 20:03:37.174939

data: Welcome to Espark from Flux -> 20:03:38.170697

data: Welcome to Espark from Flux -> 20:03:39.173778

data: Welcome to Espark from Flux -> 20:03:40.175458

data: Welcome to Espark from Flux -> 20:03:41.171465
```

### To access the Server side event endpoint 
* curl -X GET curl -X GET http://localhost:8080/router/data-stream-sse
```
id:0
event:periodic-event
data: Welcome to Espark from SSE - 20:07:49.542393

id:1
event:periodic-event
data: Welcome to Espark from SSE - 20:07:50.538262

id:2
event:periodic-event
data: Welcome to Espark from SSE - 20:07:51.541169

id:3
event:periodic-event
data: Welcome to Espark from SSE - 20:07:52.538239

id:4
event:periodic-event
data: Welcome to Espark from SSE - 20:07:53.541590
```