package ro.sda.finalproject.backend.dto;


import lombok.*;
import org.springframework.validation.annotation.Validated;
import ro.sda.finalproject.backend.entity.ProductImage;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {

    private Long id;
    private String productName;
    private Double productPrice;
    private String productDetails;
    private ProductImage productImage;
    private Long productCode;
}
