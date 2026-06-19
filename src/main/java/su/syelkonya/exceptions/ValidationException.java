package su.syelkonya.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
