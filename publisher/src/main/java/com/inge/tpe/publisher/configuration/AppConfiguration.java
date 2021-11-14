package com.inge.tpe.publisher.configuration;

import com.inge.tpe.publisher.amqp.MessageSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public Queue hello() {
        return new Queue("messages-ready");
    }

    @Bean
    public MessageSender sender() {
        return new MessageSender();
    }
}
