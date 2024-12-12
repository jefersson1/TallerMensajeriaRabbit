package com.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    // Controlador para manejar las peticiones GET
    @RestController
    public static class ConsumerController {
        private final ConsumerService consumer;

        // Inyección del servicio mediante el constructor
        public ConsumerController(ConsumerService consumer) {
            this.consumer = consumer;
        }

        // Endpoint para obtener los mensajes consumidos
        @GetMapping("/consumed/messages")
        public String consumedMessages() {
            return consumer.getInfo();
        }
    }

    // Servicio para consumir los mensajes de RabbitMQ
    @Component
    public static class ConsumerService {
        private final StringBuilder builder;

        public ConsumerService() {
            String info = "[Java-Spring] no messages for now";
            builder = new StringBuilder(info).append("<br>");
        }

        // Método que consume los mensajes de la cola de RabbitMQ
        @RabbitListener(queues = "${queue.name}")
        public void consume(@Payload String message) {
            String info = "[Java-Spring] message consumed = \"" + message + "\"";
            builder.append(info).append("<br>");
        }

        // Método para obtener los mensajes consumidos
        public String getInfo() {
            return builder.toString();
        }
    }
}
