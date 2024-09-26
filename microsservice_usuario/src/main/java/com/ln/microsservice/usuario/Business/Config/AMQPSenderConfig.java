package com.ln.microsservice.usuario.Business.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AMQPSenderConfig {

    @Value("${queue.name}")
    private String queueName;
    @Value("${professores.queue}")
    private String professoresQueue;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Queue professoresQueue() {
        return new Queue(professoresQueue, true);
    }
}
