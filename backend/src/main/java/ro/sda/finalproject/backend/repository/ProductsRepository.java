package ro.sda.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.finalproject.backend.entity.Products;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{

    Optional<Products> findProductsByAppUserEmailAndProductName(String email,String productName);

}
