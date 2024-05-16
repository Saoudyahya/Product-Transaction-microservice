package com.example.Productmicroservice.Configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {


    public static final String IMPORT_EXPORT_QUEUE = "IMPORT-EXPORT-QUEUE";
    public static final String IMPORT_EXPORT_TOPIC_EXCHANGE = "IMPORT-EXPORT-TOPIC-EXCHANGE";
    public static final String ROUTING_KEY = "Routing_key";

 @Bean
    public Queue queue(){
        return new Queue(IMPORT_EXPORT_QUEUE);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(IMPORT_EXPORT_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);

    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
     return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template ;
    }


}
