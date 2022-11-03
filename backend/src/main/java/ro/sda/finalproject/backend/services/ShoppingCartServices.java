package ro.sda.finalproject.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.finalproject.backend.dto.ShoppingCartDto;
import ro.sda.finalproject.backend.entity.AppUser;
import ro.sda.finalproject.backend.entity.CartItem;
import ro.sda.finalproject.backend.entity.Products;
import ro.sda.finalproject.backend.entity.ShoppingCart;
import ro.sda.finalproject.backend.exception.EntityNotFoundException;
import ro.sda.finalproject.backend.mapper.ShoppingCartMapper;
import ro.sda.finalproject.backend.repository.AppUserRepository;
import ro.sda.finalproject.backend.repository.CartItemRepository;
import ro.sda.finalproject.backend.repository.ProductsRepository;
import ro.sda.finalproject.backend.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartServices {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final AppUserRepository appUserRepository;

    private final ProductsRepository productsRepository;

    private final CartItemRepository cartItemRepository;

    public ShoppingCartDto getShoppingCart(String email) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByAppUserEmail(email).orElseThrow(() -> new EntityNotFoundException(String.format("No shopping-cart was found for user with email " + email)));
        return shoppingCartMapper.convertToDto(shoppingCart);
    }

    @Transactional
    public ShoppingCartDto addItemToShoppingCart(String email, String productName) {
        AppUser appUser = appUserRepository.findUserByEmail(email).orElseThrow(() -> new EntityNotFoundException(String.format("No user with email " + email + " was found")));
        Products products = productsRepository.findProductsByAppUserEmailAndProductName(email, productName).orElseThrow(() -> new EntityNotFoundException(String.format("No product with user email " + email + " and product name " + productName + " was found")));
        ShoppingCart userShoppingCart = getAppUserShoppingCart(email, appUser);
        Set<CartItem> cartItems;
        if (userShoppingCart.getCartItem() != null) {
            cartItems = userShoppingCart.getCartItem();
        } else {
            cartItems = new HashSet<>();
        }
        CartItem userCartItem = addItemToShoppingCart(userShoppingCart, products, email);
        userShoppingCart.setTotalPrice(products.getProductPrice().multiply(new BigDecimal(userCartItem.getQuantity())));
        userShoppingCart.setCreateTime(LocalDateTime.now());
        shoppingCartRepository.save(userShoppingCart);
        return shoppingCartMapper.convertToDto(userShoppingCart);
    }

    public CartItem addItemToShoppingCart(ShoppingCart shoppingCart, Products products, String email) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findCartItemsByShoppingCartAppUserEmailAndProducts(email, products);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setCreateTime(LocalDateTime.now());
            cartItemRepository.save(cartItem);
            return cartItem;
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setProducts(products);
            newCartItem.setQuantity(newCartItem.getQuantity() + 1);
            newCartItem.setCreateTime(LocalDateTime.now());
            newCartItem.setShoppingCart(shoppingCart);
            cartItemRepository.save(newCartItem);
            return newCartItem;
        }
    }

    private ShoppingCart getAppUserShoppingCart(String email, AppUser appUser) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findShoppingCartByAppUserEmail(email);
        if (shoppingCart.isPresent()) {
            return shoppingCart.get();
        }
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setAppUser(appUser);
        newShoppingCart.setCreateTime(LocalDateTime.now());
        shoppingCartRepository.save(newShoppingCart);

        appUser.setShoppingCart(newShoppingCart);
        appUserRepository.save(appUser);
        return newShoppingCart;
    }
}
