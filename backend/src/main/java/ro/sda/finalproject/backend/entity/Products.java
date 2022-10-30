package ro.sda.finalproject.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(schema = "products")
@NoArgsConstructor
public class Products implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id")
    private Long id;
    private String productName;
    private Double productPrice;
    private String productDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_image_fk",referencedColumnName = "product_image_id")
    private ProductImage productImage;

    private Long productCode;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinColumn(name = "cart_item_fk",referencedColumnName = "cart_item_id")
    private CartItem cartItem;

}
