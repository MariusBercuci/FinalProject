package ro.sda.finalproject.backend.mapper;

import ro.sda.finalproject.backend.dto.ProductsDto;
import ro.sda.finalproject.backend.entity.Products;

public class ProductsMapper implements Mapper<Products, ProductsDto> {

    @Override
    public ProductsDto convertToDto(Products entity) {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setId(entity.getId());
        productsDto.setProductName(entity.getProductName());
        productsDto.setProductPrice(entity.getProductPrice());
        productsDto.setProductDetails(entity.getProductDetails());
        productsDto.setProductIcon(entity.getProductIcon());
        productsDto.setProductCode(entity.getProductCode());
        return productsDto;

    }

    @Override
    public Products convertToEntity(ProductsDto dto) {
        Products products = new Products();
        products.setId(dto.getId());
        products.setProductName(dto.getProductName());
        products.setProductPrice(dto.getProductPrice());
        products.setProductDetails(dto.getProductDetails());
        products.setProductIcon(dto.getProductIcon());
        products.setProductCode(dto.getProductCode());
        return products;
    }
}
