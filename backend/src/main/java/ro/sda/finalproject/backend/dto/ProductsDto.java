package ro.sda.finalproject.backend.dto;


import lombok.*;
import org.springframework.validation.annotation.Validated;
import ro.sda.finalproject.backend.entity.AppUser;
import ro.sda.finalproject.backend.entity.ProductImage;

import java.math.BigDecimal;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {

    private Long id;
    private String productName;
    private BigDecimal productPrice;
    private String productDetails;
    private ProductImage productImage;
    private Long productCode;
    private AppUser appUser;
}
