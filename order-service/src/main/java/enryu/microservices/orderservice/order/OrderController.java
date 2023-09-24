package enryu.microservices.orderservice.order;

import enryu.microservices.orderservice.amqp.OrderProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public OrderDto makeNewOrder(@RequestBody OrderDto orderDto) {
        log.info("New order request");
        orderService.sendOrderProductQueue(orderDto);
        return orderDto;
    }
}
