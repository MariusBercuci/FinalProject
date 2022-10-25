package ro.sda.finalproject.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "products")
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private Long id;
    private String productName;
    private Double productPrice;
    private String productDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ProductImage productImage;

    private Long productCode;

}
