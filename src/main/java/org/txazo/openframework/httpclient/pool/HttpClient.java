package org.txazo.openframework.httpclient.pool;

public interface HttpClient {

    public void submit(HttpRequest request) throws InterruptedException;

    public void shutdown();

}
