package enryu.microservices.orderservice.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducerImpl implements OrderProducer {
    private final RabbitTemplate rabbitTemplate;

    private static final String exchange = "order-exchange";
    private static final String routingKey = "order-routing-key";

    @Override
    public void sendMsgToOrderProductQueue(Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Send message {} completed", message);
    }
}
