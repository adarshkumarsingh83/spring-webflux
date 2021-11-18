# SPRING BOOT WEB FLUX CASSANDRA CRUD OPERATIONS
---

### TO BUILD THE APPLICATION
* mvn clean package

### TO RUN
* mvn spring-boot:run


----

## CASSANDRA DOCKER OPERATIONS 

### To pull cassandra image from docker hub
* $ docker pull cassandra

### To start the cassandra container
* $ docker run --name espark-cassandra -p 9042:9042 cassandra

### for docker cassandra shell
* $ docker exec -it espark-cassandra cqlsh

### Creating Keyspace
* $ CREATE KEYSPACE espark WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

### Using keyspace
* $ USE espark;

### Describing keyspace
* $ DESCRIBE keyspaces;

### Creating table
* $ CREATE TABLE employee(
  emp_id int PRIMARY KEY,
  emp_name text,
  emp_city text,
  emp_sal varint,
  emp_phone varint
  );

### Batch Insert
* $ BEGIN BATCH
  INSERT INTO employee (emp_id, emp_city, emp_name, emp_phone, emp_sal) values(  1,'dallas','adarsh',99999999, 30000);
  INSERT INTO employee (emp_id, emp_city, emp_name, emp_phone, emp_sal) values(  2,'dallas','radha',9999999, 30000);
  APPLY BATCH;

### Selection
* $ select * from employee;
----

### To Access the api via Router
* get all api
  * curl --request GET 'http://localhost:8080/router/employees'

* get employee api
  * curl --request GET 'http://localhost:8080/router/employee/0'

* create employee api
  * curl --location --request POST 'http://localhost:8080/router/employee' \
    --header 'Content-Type: application/json' \
    --data-raw '{"id": 100,"name": "employee_100" }'
* update employee api
  * curl --location --request PUT 'http://localhost:8080/router/employee/100' \
    --header 'Content-Type: application/json' \
    --data-raw '{"id": 100,"name": "employee_1001"}'

* delete employee api
  *   curl --location --request DELETE 'http://localhost:8080/router/employee/100' 
