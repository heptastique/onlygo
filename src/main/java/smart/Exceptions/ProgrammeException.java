package smart.Exceptions;

public class ProgrammeException extends RuntimeException {
    public ProgrammeException(String message, Throwable cause) {
        super(message, cause);
    }
}
