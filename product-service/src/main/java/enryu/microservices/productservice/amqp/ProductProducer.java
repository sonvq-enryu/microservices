package enryu.microservices.productservice.amqp;

import enryu.microservices.productservice.order.OrderResponse;

public interface ProductProducer {
    void sendProductOrderQueue(OrderResponse orderResponse);
}
