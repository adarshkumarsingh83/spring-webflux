# SPRINGBOOT3 HTTP EXCHANGE 

---

### To build 
* mvn clean package 

### To run 
* mvn spring-boot:run 


### To Test the espark 
* 


### Get Request
* http://localhost:9090/espark/employees
* curl --location --request GET 'http://localhost:9090/espark/employees' --header 'Content-Type: application/json'
```
[
  {
    "id": 1,
    "firstName": "adarsh",
    "lastName": "kumar",
    "career": "It"
  },
  {
    "id": 2,
    "firstName": "radha",
    "lastName": "singh",
    "career": "IT"
  },
  {
    "id": 3,
    "firstName": "sonu",
    "lastName": "singh",
    "career": "IT"
  },
  {
    "id": 4,
    "firstName": "amit",
    "lastName": "kumar",
    "career": "Finance"
  }
]
```


### Get Request
* http://localhost:9090/espark/employee/1
* curl --location --request GET 'http://localhost:9090/espark/employee/1'
``` 
 {
  "id": 1,
  "firstName": "adarsh",
  "lastName": "kumar",
  "career": "It"
}
```

### Post Request
* http://localhost:9090/espark/employee
* curl --location --request POST 'http://localhost:9090/espark/employee' \
  --header 'Content-Type: application/json' \
  --data-raw '{"id": 5,"firstName": "shakti","lastName": "singh","career": "Operatons" }'
``` 
 {
    "id": 5,
    "firstName": "shakti",
    "lastName": "singh",
    "career": "Operatons"
}
```

### Put Request
* http://localhost:9090/espark/employee/5
* curl --location --request PUT 'http://localhost:9090/espark/employee/5' \
  --header 'Content-Type: application/json' \
  --data-raw '{"id": 5,"firstName": "shakti","lastName": "singh","career": "founder"}'
```
{
    "id": 5,
    "firstName": "shakti",
    "lastName": "singh",
    "career": "founder"
}
```

### Patch Request
* http://localhost:9090/espark/employee/5
* curl --location --request PATCH 'http://localhost:9090/espark/employee/5' \
  --header 'Content-Type: application/json' \
  --data-raw '{"career": "founder-invester"}'
``` 
 {
    "id": 5,
    "firstName": "shakti",
    "lastName": "singh",
    "career": "founder-invester"
}
```