package org.txazo.openframework.httpclient.pool;

import java.io.IOException;

public class ResponseEmptyException extends IOException {

    private static final long serialVersionUID = -2918457494503918369L;

    public ResponseEmptyException() {
        super();
    }

    public ResponseEmptyException(String message) {
        super(message);
    }

    public ResponseEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseEmptyException(Throwable cause) {
        super(cause);
    }

}
