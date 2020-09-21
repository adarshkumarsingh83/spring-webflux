# springboot-webflux-server

* mvn clean pakcage 
* mvn spring-boot:run 

* docker run -p 27017-27019:27017-27019 --name mongodb mongo
* docker ps -a 
* docker stop <'container-id'>

### To save the employee in db 
* curl -X POST -H "Content-Type: application/json" -d '{"id":1, "firstName":"adarsh","lastName":"kumar", "career":"it" }' http://localhost:8080/api/employee -v
* curl -X POST -H "Content-Type: application/json" -d '{"id":2, "firstName":"radha","lastName":"singh", "career":"it" }' http://localhost:8080/api/employee -v
* curl -X POST -H "Content-Type: application/json" -d '{"id":3, "firstName":"amit","lastName":"kumar", "career":"it" }' http://localhost:8080/api/employee -v

### To get employee by id 
* curl -v http://localhost:8080/api/employee/1

### To get all employee List
* curl -v http://localhost:8080/api/employees

### To update employee 
*  curl -X PUT -H "Content-Type: application/json" -d '{"id":1, "firstName":"adarsh","lastName":"kumar singh", "career":"it" }' http://localhost:8080/api/employee/1 -v


### To delete employee 
* curl -X DELETE  http://localhost:8080/api/employee/3 -v