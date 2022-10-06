package ro.sda.finalproject.backend.exception;

public class EmailExistException extends RuntimeException {

    public EmailExistException(String message){
        super(message);
    }
}
