package enryu.microservices.productservice.product;

import enryu.microservices.productservice.order.OrderDto;
import enryu.microservices.productservice.order.OrderResponse;
import enryu.microservices.productservice.order.ProductStatus;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    void handleNewOrder(OrderDto order);
    OrderResponse updateProductInv(Product product, int orderQuantity);
}
