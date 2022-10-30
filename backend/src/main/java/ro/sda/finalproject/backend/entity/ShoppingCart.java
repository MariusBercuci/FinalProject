package ro.sda.finalproject.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Table(name = "shopping_cart")

public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;
    private Date date;
    private Double totalPrice;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "shoppingCart")
    private Set<CartItem> cartItem = new HashSet<>();
    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER,mappedBy = "shoppingCart")
    private AppUser appUser;

}
