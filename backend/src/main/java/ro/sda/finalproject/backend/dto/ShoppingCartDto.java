package ro.sda.finalproject.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {

    private Long id;
    private LocalDateTime createTime;
    private BigDecimal totalPrice;
    private Set<CartItemDto> cartItem =new HashSet<>();
    private AppUserDto appUser;
}
