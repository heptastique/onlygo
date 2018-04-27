package smart.Exceptions;

public class ActivityException extends RuntimeException {
    public ActivityException(String message, Throwable cause) {
        super(message, cause);
    }
}
