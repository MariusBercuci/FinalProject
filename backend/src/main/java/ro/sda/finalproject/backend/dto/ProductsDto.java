package ro.sda.finalproject.backend.dto;


import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {


    private Long id;
    private String productName;
    private Double productPrice;
    private String productDetails;
    private String productIcon;
    private Long productCode;
}
