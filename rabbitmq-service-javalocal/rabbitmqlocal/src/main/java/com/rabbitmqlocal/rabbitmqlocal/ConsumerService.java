package com.rabbitmqlocal.rabbitmqlocal;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @RabbitListener(queues = "message1")
    public void consumeMessage(String message) {
        System.out.println("[Consumer] Mensaje recibido: " + message);
    }
}

