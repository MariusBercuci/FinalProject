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
public class ProductsServices{

    public ProductsRepository productsRepository;
    public ProductsMapper productsMapper;

    public List<ProductsDto> findAllProducts() {
        return productsRepository.findAll().stream().map(productsMapper::convertToDto).collect(Collectors.toList());
    }

    public ProductsDto getProductsById(Long id){
        Products products = productsRepository.findById(id).orElseThrow(() ->new ProductsNotFoundException("No products by id" + id + "not found."));
        return productsMapper.convertToDto(products);
    }

    public ProductsDto createNewProducts(ProductsDto productsDto){
        Products newProducts = productsMapper.convertToEntity(productsDto);
        Optional<Products> optionalProducts = productsRepository.findProductsById(newProducts.getId());
        if(optionalProducts.isPresent()){
            throw new IdExistException("The id" + newProducts.getId() + "is already in use");
        }else {
            productsRepository.save(newProducts);
        }
        return productsMapper.convertToDto(newProducts);
    }

    public ProductsDto updateProducts(Long id, ProductsDto productsDto){
        Products currentProducts = productsMapper.convertToEntity(productsDto);
        currentProducts.setId(productsDto.getId());
        currentProducts.setProductName(productsDto.getProductName());
        currentProducts.setProductPrice(productsDto.getProductPrice());
        currentProducts.setProductDetails(productsDto.getProductDetails());
        currentProducts.setProductIcon(productsDto.getProductIcon());
        currentProducts.setProductCode(productsDto.getProductCode());
        productsRepository.save(currentProducts);
        return productsMapper.convertToDto(currentProducts);
    }

    public void deleteProducts(Long id){productsRepository.deleteById(id);}

}
