package ro.sda.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.finalproject.backend.entity.CartItem;
import ro.sda.finalproject.backend.entity.Products;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findCartItemsByShoppingCartAppUserEmailAndProducts(String email, Products products);
}
