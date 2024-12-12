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
