package space.tscg.common.dotenv;

/**
 * Signals that dotenv exception of some sort has occurred.
 */
public class DotenvException extends RuntimeException {
    private static final long serialVersionUID = 6550159518654821234L;

    /**
     * Create a dotenv runtime exception with the specified detail message
     * @param message the detail message
     */
    public DotenvException(String message) {
        super(message);
    }

    /**
     * Creates a dotenv runtime exception
     * @param cause the cause
     */
    public DotenvException(Throwable cause) {
        super(cause);
    }
}
