package org.txazo.wyot.netty;

public class NettyRemotingException extends Exception {

    private static final long serialVersionUID = 8645329574854235174L;

    public NettyRemotingException() {
    }

    public NettyRemotingException(String message) {
        super(message);
    }

    public NettyRemotingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NettyRemotingException(Throwable cause) {
        super(cause);
    }

}
