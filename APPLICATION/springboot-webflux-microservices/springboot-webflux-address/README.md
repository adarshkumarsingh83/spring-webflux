# ADDRESS SERVICE 

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
* $ use address

### save and fetch operation via mongo shell
```
$ db.address.save({ id: 1, streetName: "street 1", cityName: "citi 1", country: "country 1" })
$ db.address.save({ id: 2, streetName: "street 2", cityName: "citi 2", country: "country 2" })
$ db.address.find({ id: 3, streetName: "street 3", cityName: "citi 3", country: "country 3" })
```
---

### To Access the api via Router
* get all api
    * curl --request GET 'http://localhost:8080/router/addresses'

* get address api
    * curl --request GET 'http://localhost:8080/router/address/1'

* create address api
    * curl --location --request POST 'http://localhost:8080/router/address' \
      --header 'Content-Type: application/json' \
      --data-raw '{"id": 4, "streetName": "street 4", "cityName": "citi 4", "country": "country 4" }'
  
* update address api
    * curl --location --request PUT 'http://localhost:8080/router/address/4' \
      --header 'Content-Type: application/json' \
      --data-raw '{"id": 4, "streetName": "street 4.0", "cityName": "citi 4.0", "country": "country 4.0" }'

* delete address api
    *   curl --location --request DELETE 'http://localhost:8080/router/address/3' 
