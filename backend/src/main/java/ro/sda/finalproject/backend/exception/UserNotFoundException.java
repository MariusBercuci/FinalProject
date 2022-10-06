package ro.sda.finalproject.backend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String messege) {
        super(messege);
    }
}
