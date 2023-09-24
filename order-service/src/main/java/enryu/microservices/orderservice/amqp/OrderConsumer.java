package enryu.microservices.orderservice.amqp;

public interface OrderConsumer {

    void sendMessage(Object message);
}
