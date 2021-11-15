package com.inge.tpe.publisher.amqp;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public boolean send(String message) {
        try {
            this.template.convertAndSend(queue.getName(), message);
        } catch (AmqpException e) {
            System.out.println("Error: [x] Sending '" + message + "'");
            return false;
        }
        System.out.println(" [x] Sent '" + message + "'");
        return true;
    }
}
