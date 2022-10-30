package ro.sda.finalproject.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {

    private Long id;
    private Date date;
    private Double totalPrice;
    private Set<CartItemDto> cartItem =new HashSet<>();
    private AppUserDto appUser;
}
