# SPRING BOOT WEB FLUX RABBITMQ 

---





### To Pull the docker Rabbitmq image from docker hub
* $ docker pull rabbitmq:3-management

### To Run the docker Rabbitmq with Management
* $ docker run  --hostname espark-rabbitmq --name espark-rabbitmq -p 15672:15672 -p 5672:5672  rabbitmq:3-management

### To Access the Management Console 
* http://localhost:15672/
* guest/guest

### To check logs
* $ docker logs espark-rabbitmq

### To check status
* $ docker exec espark-rabbitmq rabbitmqctl status

### To list the queue 
* $ docker exec espark-rabbitmq rabbitmqctl list_queues

