package enryu.microservices.productservice.product;

import enryu.microservices.productservice.amqp.ProductProducer;
import enryu.microservices.productservice.order.OrderDto;
import enryu.microservices.productservice.order.OrderResponse;
import enryu.microservices.productservice.order.ProductStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final ProductProducer productProducer;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleNewOrder(OrderDto order) {
        OrderResponse orderResponse = new OrderResponse();
        Optional<Product> productOpt = productDao.findById(order.getProductId());
        if (productOpt.isEmpty()) {
            orderResponse.setProductStatus(ProductStatus.STOP_SALE);
            orderResponse.setOrderSuccess(false);
        } else {
            orderResponse = updateProductInv(productOpt.get(), order.getQuantity());
        }
        productProducer.sendProductOrderQueue(orderResponse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderResponse updateProductInv(Product product, int orderQuantity) {
        if (isEnoughInventory(product.getQuantity(), orderQuantity)) {
            return new OrderResponse(false, ProductStatus.NOT_ENOUGH_INVENTORY);
        }
        product.setQuantity(product.getQuantity() - orderQuantity);
        productDao.saveAndFlush(product);
        return new OrderResponse(true, ProductStatus.OK);
    }

    private boolean isEnoughInventory(int productQuantity, int orderQuantity) {
        return productQuantity >= orderQuantity;
    }
}
