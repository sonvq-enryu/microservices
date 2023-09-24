package enryu.microservices.orderservice.amqp;

public interface OrderProducer {
    void sendMsgToOrderProductQueue(Object message);
}
