package com.rabbitmq.producer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableRabbit
public class ProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @RestController
    public class ProducerController {
        @Autowired
        private ProducerService producer;

        // El endpoint se ha cambiado para que funcione correctamente sin @RequestMapping adicional
        @GetMapping("/publish/{message}")
        public String publish(@PathVariable String message) {
            producer.publish(message);
            return "Message published: " + message;
        }
    }

    @Component // Asegura que el servicio sea gestionado como un bean por Spring
    public class ProducerService {

        @Autowired
        private RabbitTemplate rabbitTemplate;

        @Value("${queue.name}")
        private String queue;

        public void publish(String message) {
            rabbitTemplate.convertAndSend(queue, message);
        }
    }
}
