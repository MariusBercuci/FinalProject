package ro.sda.finalproject.backend.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class ProductsDto {


    private Long id;
    private String productName;
    private Double productPrice;
    private String productDetails;
    private String productIcon;
    private Long productCode;
}
