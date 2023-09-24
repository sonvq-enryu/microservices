package enryu.microservices.productservice.amqp;

import enryu.microservices.productservice.order.OrderDto;
import enryu.microservices.productservice.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderConsumerImpl implements OrderConsumer {

    @RabbitListener(queues = "${broker.rabbit.queue.order}")
    public void consume(OrderDto message) {
        log.info("Consume message: {}", message);

    }
}
