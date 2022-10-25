package ro.sda.finalproject.backend.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.sda.finalproject.backend.dto.ProductsDto;
import ro.sda.finalproject.backend.entity.ProductImage;
import ro.sda.finalproject.backend.exception.IdExistException;
import ro.sda.finalproject.backend.services.ProductsServices;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path = "/api/products")
@AllArgsConstructor
public class ProductsController {


    private final ProductsServices productsServices;


    @GetMapping("/all")
    public ResponseEntity<List<ProductsDto>> getAllProducts() {
        List<ProductsDto> productsDtoList = productsServices.findAllProducts();
        return new ResponseEntity<>(productsDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<ProductsDto> getProductsById(@PathVariable("id") Long id) {
        ProductsDto productsDto = productsServices.getProductsById(id);
        return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ProductsDto> createNewProducts(@RequestParam("imageFile") MultipartFile file,
                                                         @RequestParam("productName") String productName,
                                                         @RequestParam("productPrice") Double productPrice,
                                                         @RequestParam("productDetails") String productDetails,
                                                         @RequestParam("productCode") Long productCode)
            throws IdExistException {

        ProductsDto newProductsDto = new ProductsDto();
        newProductsDto.setProductDetails(productDetails);
        newProductsDto.setProductName(productName);
        newProductsDto.setProductPrice(productPrice);
        newProductsDto.setProductCode(productCode);
        try {
            newProductsDto.setProductImage(uploadImage(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newProductsDto = productsServices.createNewProducts(newProductsDto);
        return new ResponseEntity<>(newProductsDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductsDto> updateProduct(@PathVariable("id") Long productId, MultipartFile file) {
        ProductsDto searchedProduct = productsServices.getProductsById(productId);
        ProductImage images;
        try {
            images = uploadImage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        searchedProduct.setProductImage(images);

        ProductsDto newProductsDto = productsServices.updateProducts(productId, searchedProduct);
        return new ResponseEntity<>(newProductsDto, HttpStatus.OK);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable("id") Long productId) {
        productsServices.deleteProducts(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ProductImage uploadImage(MultipartFile file) throws IOException {

        return ProductImage.builder()
                .imageName(file.getOriginalFilename())
                .imageType(file.getContentType())
                .picByte(file.getBytes())
                .build();
    }
}
