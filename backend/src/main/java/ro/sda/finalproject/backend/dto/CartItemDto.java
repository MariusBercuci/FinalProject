package ro.sda.finalproject.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private Integer quantity;
    private LocalDateTime createTime;
}
