package ro.sda.finalproject.backend.exception;

public class ProductsNotFoundException extends RuntimeException{
    public ProductsNotFoundException(String message){
        super(message);
    }
}
