package org.txazo.openframework.httpclient.pool;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {

    // 重试次数
    private final int retryCount;

    public DefaultHttpRequestRetryHandler(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        if (executionCount >= retryCount) {
            return false;
        }
        if (exception instanceof InterruptedIOException) {
            return false;
        }
        if (exception instanceof UnknownHostException) {
            return false;
        }
        if (exception instanceof ConnectTimeoutException) {
            return false;
        }
        if (exception instanceof SSLException) {
            return false;
        }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        return !(request instanceof HttpEntityEnclosingRequest);
    }

}
