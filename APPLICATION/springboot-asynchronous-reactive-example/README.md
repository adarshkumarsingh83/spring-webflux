# springboot-asynchronous-reactive-example

--

### To Build 
* mvn clean package

### To Run 
* mvn spring-boot:run 


### To Access Api via router
* To Access Non Stream Api
  * curl --request GET 'http://localhost:8080/router/data-list?limit=10'
* To Access Stream Api
  * curl --request GET 'http://localhost:8080/router/data-stream/10'
  
### To Access Api via controller
* To Access Non Stream Api 
  * curl --request GET 'http://localhost:8080/controller/data-list/10' 
* To Access Stream Api 
  * curl --request GET 'http://localhost:8080/controller/data-stream/10'