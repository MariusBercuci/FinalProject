package ro.sda.finalproject.backend.mapper;

import org.springframework.stereotype.Service;
import ro.sda.finalproject.backend.dto.ProductsDto;
import ro.sda.finalproject.backend.entity.Products;
@Service
public class ProductsMapper implements Mapper<Products, ProductsDto> {

    @Override
    public ProductsDto convertToDto(Products entity) {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setProductId(entity.getProductId());
        productsDto.setProductName(entity.getProductName());
        productsDto.setProductPrice(entity.getProductPrice());
        productsDto.setProductDetails(entity.getProductDetails());
        productsDto.setProductImage(entity.getProductImage());
        productsDto.setProductCode(entity.getProductCode());
        return productsDto;

    }

    @Override
    public Products convertToEntity(ProductsDto dto) {
        Products products = new Products();
        products.setProductId(dto.getProductId());
        products.setProductName(dto.getProductName());
        products.setProductPrice(dto.getProductPrice());
        products.setProductDetails(dto.getProductDetails());
        products.setProductImage(dto.getProductImage());
        products.setProductCode(dto.getProductCode());
        return products;
    }
}
