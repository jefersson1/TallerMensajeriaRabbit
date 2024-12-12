## Java Dockerfile Compose ejecución

#### Desplegamos los contendores:

docker-compose -f broker-consumer-producer.yml up -d
docker-compose -f broker-consumer2-producer2.yml up -d


#### Nota: No levantar ambas infraestructuras (Conflictos de nombres y puertos). Si desea probar la otra usar el comando down primero.


#### (CASO 1) Acceder a los contenedores:
##### Broker: http://localhost:15672/
##### Productor: http://localhost:8081/publish/{message}
##### Consumidor: http://localhost:8082/consumed/messages
#### (CASO 2) Acceder a los contenedores:
##### Broker: http://localhost:15672/
##### Productor-1: http://localhost:8081/publish/{message}
##### Productor-2: http://localhost:8082/publish/{message}
##### Consumidor-1: http://localhost:8083/consumed/messages
##### Consumidor-2: http://localhost:8084/consumed/messages

for ($i = 1; $i -le 1000; $i++) { curl -X GET http://localhost:8081/publish/HelloWorld }
for ($i = 1; $i -le 1000; $i++) { curl -X GET http://localhost:8082/publish/HelloWorld }
