package ro.sda.finalproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.finalproject.backend.entity.Products;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
//@Transactional
public interface ProductsRepository extends JpaRepository<Products, Long>{
        Optional<Products> findProductsById(Long id);

}
