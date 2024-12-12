
## Java Dockerfile ejecución


#### Construimos las imágenes, la red, y el broker respectivamente:

docker build -t java-consumer .
docker build -t java-producer .

docker network create red-1
docker run --network red-1 --name broker-1 -p5672:5672 -p15672:15672 -d rabbitmq:management
docker run --network red-1 --name java-producer-1 -p8081:8081 -d java-producer
docker run --network red-1 --name java-consumer-1 -p8082:8082 -d java-consumer


#### URls:

##### Broker: http://localhost:15672/
##### Productor: http://localhost:8081/publish/{message}
##### Consumidor: http://localhost:8082/consumed/messages

for ($i = 1; $i -le 1000; $i++) { curl -X GET http://localhost:8081/publish/HelloWorld }
