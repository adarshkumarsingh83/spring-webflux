# springboot-asynchronous-reactive-example

--

### To Build 
* mvn clean package

### To Run 
* mvn spring-boot:run 

### To Access Api
* To Access Non Stream Api 
  * curl  --request GET 'http://localhost:8080/data-list/10' 
* To Access Stream Api 
  * curl  --request GET 'http://localhost:8080/data-stream/10'