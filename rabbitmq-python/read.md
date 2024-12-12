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
