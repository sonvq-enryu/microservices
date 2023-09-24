package enryu.microservices.orderservice.order;

import enryu.microservices.orderservice.amqp.OrderProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderProducer orderProducer;
    private final OrderDao orderDao;
    @Override
    public void sendOrderProductQueue(OrderDto order) {
        orderProducer.sendMsgToOrderProductQueue(order);
    }

    @Override
    public void newOrder(OrderDto orderDto) {
        log.info("Create new order");
        Order order = Order.builder()
                .orderStatus(OrderStatus.PENDING)
                .quantity(orderDto.getQuantity())
                .customerId(orderDto.getCustomerId())
                .productId(orderDto.getProductId())
                .build();
        orderDao.saveAndFlush(order);
        this.sendOrderProductQueue(orderDto);
        log.info("Create new order completed");
    }
}
