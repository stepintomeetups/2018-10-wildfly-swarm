package io.stepinto.wildfly.swarm.demo.common.exception;

/**
 * Entitás nem található kivétel
 *
 * @author Nandor Holozsnyak
 */
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException() {
        super();
        errorCode = "ENTITY_NOT_FOUND";
    }

    public EntityNotFoundException(String message) {
        super(message);
        errorCode = "ENTITY_NOT_FOUND";
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
        errorCode = "ENTITY_NOT_FOUND";
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
        errorCode = "ENTITY_NOT_FOUND";
    }
}
