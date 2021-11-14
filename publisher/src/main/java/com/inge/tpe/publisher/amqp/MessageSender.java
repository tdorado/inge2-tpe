package com.inge.tpe.publisher.amqp;

import com.inge.tpe.publisher.models.Message;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public Message send(Message message) {
        try {
            this.template.convertAndSend(queue.getName(), message.getTextMessage());
        } catch (AmqpException e) {
            System.out.println("Error: [x] Sending '" + message + "'");
        }
        System.out.println(" [x] Sent '" + message + "'");
        return message;
    }
}
