package enryu.microservices.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${broker.rabbit.queue.order}")
    private String orderQueue;

    @Value("${broker.rabbit.queue.product}")
    private String productQueue;

    @Value("${broker.rabbit.exchange.topic.order}")
    private String orderExchange;

    @Value("${broker.rabbit.exchange.topic.product}")
    private String productExchange;

    @Value("${broker.rabbit.routing_key.order}")
    private String orderRoutingKey;

    @Value("${broker.rabbit.routing_key.product}")
    private String productRoutingKey;

    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueue);
    }

    @Bean Queue productQueue() {
        return new Queue(productQueue);
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(orderExchange);
    }

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(productExchange);
    }

    @Bean
    public Binding declareBidingOrder() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(orderRoutingKey);
    }

    @Bean
    public Binding declareBidingProduct() {
        return BindingBuilder.bind(productQueue()).to(productExchange()).with(productRoutingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}