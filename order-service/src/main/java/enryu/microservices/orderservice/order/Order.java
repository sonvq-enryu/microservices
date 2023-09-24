package enryu.microservices.orderservice.order;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(
        name = "order",
        indexes = {
                @Index(name = "customer_index", columnList = "customerId")
        })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_serial"
    )
    @SequenceGenerator(
            name = "order_serial",
            allocationSize = 10
    )
    private Long orderId;

    @Column(name = "product_id")
    @Nonnull
    private Long productId;

    @Column(name = "quantity")
    @Nonnull
    private Integer quantity;

    @Column(name = "customer_id")
    @Nonnull
    private Long customerId;

    @Column(name = "order_status")
    @Enumerated(value = EnumType.ORDINAL)
    private OrderStatus orderStatus;
}
