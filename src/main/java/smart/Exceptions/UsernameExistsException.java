package smart.Exceptions;

public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException(final String message) {
        super(message);
    }
}
