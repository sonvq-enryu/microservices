package enryu.microservices.productservice.amqp;


import enryu.microservices.productservice.order.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductProducerImpl implements ProductProducer {

    private final String queue = "product-queue";
    private final String exchange = "product-exchange";

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendProductOrderQueue(OrderResponse orderResponse) {
        log.info("Send order response {}", orderResponse);
        rabbitTemplate.convertAndSend(exchange, queue, orderResponse);
    }
}
