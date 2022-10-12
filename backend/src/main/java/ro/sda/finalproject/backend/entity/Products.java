package ro.sda.finalproject.backend.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Data
@Table(schema = "products")
@NoArgsConstructor
@Getter
@Setter
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String productName;
    private Double productPrice;
    private String productDetails;
    private String productIcon;
    private Long productCode;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productDetails='" + productDetails + '\'' +
                ", productIcon='" + productIcon + '\'' +
                "', productCode='" + productCode + '\'' +
                '}';
    }
}
