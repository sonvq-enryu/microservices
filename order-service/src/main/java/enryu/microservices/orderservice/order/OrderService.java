package enryu.microservices.orderservice.order;

public interface OrderService {
    void sendOrderProductQueue(OrderDto order);
    void newOrder(OrderDto orderDto);
}
