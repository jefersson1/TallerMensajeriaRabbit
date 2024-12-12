## Versión Completa


#### Levantar la infraestructura deseada:

##### Caso1: docker-compose -f broker-consumer-producer-java.yml up -d
##### Caso2: docker-compose -f broker-consumer-producer-python.yml up -d
##### Caso3: docker-compose -f broker-consumer2-producer2-java.yml up -d
##### Caso4: docker-compose -f broker-consumer2-producer2-python.yml up -d


#### Nota: No levantar varias infraestructuras simultáneamente (Conflictos de nombres y puertos). ejecutar docker-compose nombre_infraestrura down primero.

#### Rutas 1 Caso:

##### Broker: http://localhost:15672/
##### Productor: http://localhost:8081/publish/{message}
##### Consumidor: http://localhost:8082/consumed/messages

#### Rutas 2 caso:


##### Broker: http://localhost:15672/
##### Productor-1: http://localhost:8081/publish/{message}
##### Productor-2: http://localhost:8082/publish/{message}
##### Consumidor-1: http://localhost:8083/consumed/messages
##### Consumidor-2: http://localhost:8084/consumed/messages


#### Nota:  Cuando hay más de un Consumidor los mensajes siguen el Round-Robin-Protocol (Se turnaran para recibir los mensajes).
