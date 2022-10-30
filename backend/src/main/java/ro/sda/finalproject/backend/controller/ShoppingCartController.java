package ro.sda.finalproject.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.sda.finalproject.backend.dto.ShoppingCartDto;
import ro.sda.finalproject.backend.services.ShoppingCartServices;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/cart")
public class ShoppingCartController {

    private ShoppingCartServices shoppingCartServices;

    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCartDto>> getAllShoppingCarts() {
        List<ShoppingCartDto> shoppingCartDtoList = shoppingCartServices.getAllShoppingCarts();
        return new ResponseEntity<>(shoppingCartDtoList, HttpStatus.OK);
    }
}
