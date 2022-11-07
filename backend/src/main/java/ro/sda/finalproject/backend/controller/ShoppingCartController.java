package ro.sda.finalproject.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.finalproject.backend.dto.ShoppingCartDto;
import ro.sda.finalproject.backend.services.ShoppingCartServices;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/cart")
public class ShoppingCartController {

    private final ShoppingCartServices shoppingCartServices;

    @GetMapping("/find/{email}")
    public ResponseEntity<ShoppingCartDto> getShoppingCart(@PathVariable("email") String email) {
        ShoppingCartDto shoppingCart = shoppingCartServices.getShoppingCart(email);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PostMapping("/add/product")
    public ResponseEntity<ShoppingCartDto> addProductToShoppingCart(@RequestParam("email") String email,
                                                                    @RequestParam("productName") String productName) {
        ShoppingCartDto shoppingCart = shoppingCartServices.addItemToShoppingCart(email, productName);
        return new ResponseEntity<>(shoppingCart, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/cart")
    public ResponseEntity<?> deleteShoppingCart(@RequestParam("email") String email) {
        shoppingCartServices.deleteShoppingCart(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/item")
    public ResponseEntity<?> deleteItemFromShoppingCart(@RequestParam("email") String email,
                                                        @RequestParam("productName") String productName){
        shoppingCartServices.deleteItemFromShoppingCart(email,productName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
