package ro.sda.finalproject.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.ShoppingCartDto;
import ro.sda.finalproject.backend.mapper.ShoppingCartMapper;
import ro.sda.finalproject.backend.repository.ShoppingCartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartServices {

    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartMapper shoppingCartMapper;
    public List<ShoppingCartDto> getAllShoppingCarts() {
        return shoppingCartRepository.findAll().stream().map(shoppingCartMapper::convertToDto).collect(Collectors.toList());
    }
}
