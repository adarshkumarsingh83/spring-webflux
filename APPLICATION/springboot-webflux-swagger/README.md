# SPRINGBOOT WEBFLUX SWAGGER 

### TO BUILD 
* mvn clean package

### TO RUN 
* mvn spring-boot:run 

### TO ACCESS THE SWAGGER 
* http://localhost:8080/swagger-doc/swagger-ui.html

### To Access the api via Router 
* get all api 
  * curl --request GET 'http://localhost:9090/router/employees'

* get employee api 
  * curl --request GET 'http://localhost:9090/router/employee/0'

* create employee api 
  * curl --location --request POST 'http://localhost:8080/router/employee' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "id": 100,
    "name": "employee_100"
    }'
* update employee api 
  * curl --location --request PUT 'http://localhost:8080/router/employee/100' \
     --header 'Content-Type: application/json' \
     --data-raw '{
     "id": 100,
     "name": "employee_1001"
     }'

* delete employee api 
  *   curl --location --request DELETE 'http://localhost:8080/router/employee/100' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "id": 100,
      "name": "employee_1001"
      }'
