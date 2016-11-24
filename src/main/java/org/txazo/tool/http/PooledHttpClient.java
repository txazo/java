package org.txazo.tool.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.*;

/**
 * Http请求池
 *
 * @author xiaozhou.tu
 * @date 2016-10-25
 */
public class PooledHttpClient {

    private final CloseableHttpClient httpClient;
    private final ExecutorService threadPool;

    private PooledHttpClient(int fixedSize, int timeout) {
        threadPool = new ThreadPoolExecutor(fixedSize, fixedSize, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000), Executors.defaultThreadFactory());

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .build();

        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }

    public static PooledHttpClient getInstance(int fixedSize, int timeout) {
        return new PooledHttpClient(fixedSize, timeout);
    }

    public void get(String url) {
        submit(new HttpGet(url));
    }

    private void submit(HttpRequestBase request) {
        threadPool.submit(new HttpReuqestTask(request));
    }

    private class HttpReuqestTask implements Runnable {

        private HttpRequestBase request;

        public HttpReuqestTask(HttpRequestBase request) {
            this.request = request;
        }

        @Override
        public void run() {
            try {
                CloseableHttpResponse response = httpClient.execute(request);
                if (response != null && response.getStatusLine() != null && response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        System.out.println(EntityUtils.toString(entity));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
