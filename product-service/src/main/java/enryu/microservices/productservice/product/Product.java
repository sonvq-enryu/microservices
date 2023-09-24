package enryu.microservices.productservice.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_serial")
    @SequenceGenerator(name = "product_serial", allocationSize = 10)
    private Long productId;
    private Integer quantity = 0;
    private Long name;
}
