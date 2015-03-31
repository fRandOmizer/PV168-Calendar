package Exceptions;

/**
 * Created by Peter on 25.3.2015.
 */
public class ServiceFailureException extends RuntimeException {
    public ServiceFailureException(String msg) {
        super(msg);
    }

    public ServiceFailureException(Throwable cause) {
        super(cause);
    }

    public ServiceFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
