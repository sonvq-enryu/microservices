package enryu.microservices.orderservice.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {
    private Long orderId;
    private Long customerId;
    private Long productId;
    private Integer quantity;
}
