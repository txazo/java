package org.txazo.wyot.mapping;

public class ObjectMappingException extends RuntimeException {

    private static final long serialVersionUID = -4381518697058287307L;

    public ObjectMappingException() {
    }

    public ObjectMappingException(String message) {
        super(message);
    }

    public ObjectMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectMappingException(Throwable cause) {
        super(cause);
    }

}
