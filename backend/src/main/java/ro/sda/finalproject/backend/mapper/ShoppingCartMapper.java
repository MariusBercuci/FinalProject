package ro.sda.finalproject.backend.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.AppUserDto;
import ro.sda.finalproject.backend.dto.CartItemDto;
import ro.sda.finalproject.backend.dto.ShoppingCartDto;
import ro.sda.finalproject.backend.entity.AppUser;
import ro.sda.finalproject.backend.entity.CartItem;
import ro.sda.finalproject.backend.entity.ShoppingCart;
import ro.sda.finalproject.backend.repository.ShoppingCartRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartMapper implements Mapper<ShoppingCart, ShoppingCartDto> {

    private ShoppingCartRepository shoppingCartRepository;


    @Override
    public ShoppingCartDto convertToDto(ShoppingCart entity) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(entity.getId());
        shoppingCartDto.setDate(entity.getDate());
        shoppingCartDto.setTotalPrice(entity.getTotalPrice());
        if (entity.getAppUser() != null) {
            shoppingCartDto.setAppUser(appUserDto(entity.getAppUser()));
        }
        if (entity.getCartItem() != null) {
            shoppingCartDto.setCartItem(entity.getCartItem().stream().map(cartItem -> cartItemDto(cartItem)).collect(Collectors.toSet()));
        }
        return shoppingCartDto;
    }

    private CartItemDto cartItemDto(CartItem entity) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(entity.getId());
        cartItemDto.setQuantity(entity.getQuantity());
        cartItemDto.setDate(entity.getDate());
        return cartItemDto;

    }

    private AppUserDto appUserDto(AppUser entity) {
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setId(entity.getId());
        appUserDto.setFirstName(entity.getFirstName());
        appUserDto.setLastName(entity.getLastName());
        appUserDto.setPassword(entity.getPassword());
        appUserDto.setEmail(entity.getEmail());
        appUserDto.setPhone(entity.getPhone());
        appUserDto.setRole(entity.getRole());
        return appUserDto;
    }

    @Override
    public ShoppingCart convertToEntity(ShoppingCartDto dto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(dto.getId());
        shoppingCart.setDate(dto.getDate());
        shoppingCart.setTotalPrice(dto.getTotalPrice());
        if (dto.getAppUser() != null) {
            shoppingCart.setAppUser(shoppingCartRepository.getReferenceById(dto.getId()).getAppUser());
        }
        if (dto.getCartItem() != null) {
            shoppingCart.setCartItem(shoppingCartRepository.getReferenceById(dto.getId()).getCartItem());
        }
        return shoppingCart;
    }
}
