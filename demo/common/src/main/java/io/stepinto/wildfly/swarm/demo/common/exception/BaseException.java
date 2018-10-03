package io.stepinto.wildfly.swarm.demo.common.exception;

/**
 * Fő kivétel típus.
 *
 * @author Nandor Holozsnyak
 */
public class BaseException extends Exception {

    protected String errorCode;

    public BaseException() {
        super();
        errorCode = "BASE_EXCEPTION";
    }

    public BaseException(String message) {
        super(message);
        errorCode = "BASE_EXCEPTION";
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        errorCode = "BASE_EXCEPTION";
    }

    public BaseException(Throwable cause) {
        super(cause);
        errorCode = "BASE_EXCEPTION";
    }

    public String getErrorCode() {
        return errorCode;
    }
}
