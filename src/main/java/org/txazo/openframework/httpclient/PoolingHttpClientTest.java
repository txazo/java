package org.txazo.openframework.httpclient;

import org.junit.Test;
import org.txazo.openframework.httpclient.pool.HttpClient;
import org.txazo.openframework.httpclient.pool.HttpRequest;
import org.txazo.openframework.httpclient.pool.HttpResponseCallback;
import org.txazo.openframework.httpclient.pool.PoolingHttpClient;

public class PoolingHttpClientTest {

    @Test
    public void test() throws Exception {
        HttpRequest request = new HttpRequest("http://sandbox.api.e.qq.com/ads/v3/targeting_audience/create");
        request.setCallback(new HttpResponseCallback() {

            @Override
            public void callback(String result) throws Exception {
                System.out.println(result);
            }
        });

        HttpClient httpClient = PoolingHttpClient.getInstance(1, 1, 5);
        for (int i = 0; i < 10; i++) {
            httpClient.submit(request);
        }
        httpClient.shutdown();
    }

}
