# TallerMensajeriaRabbit

## Python ejecución


#### Construimos las imágenes, la red, y el broker respectivamente:

docker build -t producer ./producer-python 

docker build -t consumer ./consumer-python

docker run --network red-1 --name broker-1 -p5672:5672 -p15672:15672 -d rabbitmq:management

docker ps

#### Nota: Únicamente si no se ha agregado la librería a los dockerfile
{
docker run -it --network red-1 python:3.11 bash

pip install pika

}

docker run --network red-1 --name producer-1 -p8081:8081 -d producer
docker run --network red-1 --name consumer-1 -p8082:8082 -d consumer

#### ejecutamos un ciclo de peticiones en consola para ver como se crean y se consumen los mensajes en el broker:

for ($i = 1; $i -le 1000; $i++) { curl -X GET http://localhost:8081/publish/HelloWorld }

docker stop $(docker ps -q)


## Java ejecución

#### Instalar el service de RabbitMQ en windows

RabbitMQ no se ejecuta directamente desde la línea de comandos a menos que esté correctamente instalado. Debes instalarlo primero junto con Erlang, que es un requisito.

Para iniciar o detener el servicio 

rabbitmq-service start
rabbitmq-service stop 

Si el comando rabbitmq-service no funciona, verificar que rabbitmq_server esté en en el PATH de las variables de entorno, si no lo está agregarlo, por defecto es la siguiente ruta C:\Program Files\RabbitMQ Server\rabbitmq_server-<versión>\sbin.

El panel de administración de RabbitMQ requiere que el plugin rabbitmq_management esté habilitado.

Para habilitarlo: rabbitmq-plugins enable rabbitmq_management

Entrar a: http://localhost:15672/
Ir a la pestaña: [Queues], Seleccionar: [Add a new queue], Solo ingresar el campo [Name: message 1],Y oprimir el botón: [Add queue]

Compilar y correr el proyecto de java: Envía un mensaje entrando a: http://localhost:8080/publish/{message} Enviar el mensaje y ver como en la consola se envía y consume, o ejecuta en consola el siguiente comando for ($i = 1; $i -le 1000; $i++) { curl "http://localhost:8080/publish?message=HolaMundo" }




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
