package ro.sda.finalproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.finalproject.backend.dto.ProductsDto;
import ro.sda.finalproject.backend.exception.IdExistException;
import ro.sda.finalproject.backend.services.ProductsServices;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/api/products")
public class ProductsController {

    @Autowired
    private ProductsServices productsServices;

    @GetMapping("/all")
    public ResponseEntity<List<ProductsDto>> getAllProducts(){
        List<ProductsDto> productsDtoList = productsServices.findAllProducts();
        return new ResponseEntity<>(productsDtoList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductsDto> getProductsById(@PathVariable("id") Long id){
        ProductsDto productsDto = productsServices.getProductsById(id);
        return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductsDto> createNewProducts(@RequestBody @Valid ProductsDto productsDto) throws IdExistException{
        ProductsDto newProductsDto = productsServices.createNewProducts(productsDto);
        return new ResponseEntity<>(newProductsDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsDto> updateProducts(@PathVariable("id") Long id, @RequestBody @Valid ProductsDto productsDto){
        ProductsDto newProductsDto = productsServices.updateProducts(id, productsDto);
        return new ResponseEntity<>(newProductsDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable("id")Long id){
        productsServices.deleteProducts(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
