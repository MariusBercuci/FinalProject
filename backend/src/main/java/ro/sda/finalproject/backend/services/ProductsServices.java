package ro.sda.finalproject.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.ProductsDto;
import ro.sda.finalproject.backend.entity.Products;
import ro.sda.finalproject.backend.exception.IdExistException;
import ro.sda.finalproject.backend.exception.ProductsNotFoundException;
import ro.sda.finalproject.backend.mapper.ProductsMapper;
import ro.sda.finalproject.backend.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsServices {

    private ProductsRepository productsRepository;
    private ProductsMapper productsMapper;

    public List<ProductsDto> findAllProducts() {
        return productsRepository.findAll().stream().map(productsMapper::convertToDto).collect(Collectors.toList());
    }

    public ProductsDto getProductsById(Long productId) {
        Products products = productsRepository.findById(productId).orElseThrow(() -> new ProductsNotFoundException("No products by id" + productId + "not found."));
        return productsMapper.convertToDto(products);
    }


    public ProductsDto createNewProducts(ProductsDto productsDto) {
        Products newProducts = productsMapper.convertToEntity(productsDto);
        if (newProducts.getId() != null) {
            Optional<Products> optionalProducts = productsRepository.findById(newProducts.getId());
            if (optionalProducts.isPresent()) {
                throw new IdExistException("The id" + newProducts.getId() + "is already in use");
            }
        }
        productsRepository.save(newProducts);

        return productsMapper.convertToDto(newProducts);
    }

    public ProductsDto updateProducts(Long productId,ProductsDto productsDto) {
        Products currentProducts = productsMapper.convertToEntity(productsDto);
        currentProducts.setId(productsDto.getId());
        currentProducts.setProductName(productsDto.getProductName());
        currentProducts.setProductPrice(productsDto.getProductPrice());
        currentProducts.setProductDetails(productsDto.getProductDetails());
        currentProducts.setProductImage(productsDto.getProductImage());
        currentProducts.setProductCode(productsDto.getProductCode());
        productsRepository.save(currentProducts);
        return productsMapper.convertToDto(currentProducts);
    }

    public void deleteProducts(Long id) {
        productsRepository.deleteById(id);
    }

}
