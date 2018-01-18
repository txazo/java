package org.txazo.openframework.httpclient.pool;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Http请求池
 *
 * @author xiaozhou.tu
 * @date 2016-12-02
 */
public class PoolingHttpClient implements HttpClient {

    private static final RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig.custom()
            .setSocketTimeout(5000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .build();

    private final CloseableHttpClient httpClient;
    private final HttpCounter counter = new HttpCounter();
    // 请求阻塞队列
    private final BlockingQueue<HttpRequest> requestQueue;
    // 工作线程
    private final List<HttpWorker> workers = new ArrayList<>();

    private PoolingHttpClient(int workSize, int queueSize, int retryCount) {
        this(workSize, queueSize, retryCount, DEFAULT_REQUEST_CONFIG);
    }

    private PoolingHttpClient(int workSize, int queueSize, int retryCount, RequestConfig requestConfig) {
        if (workSize <= 0) {
            throw new IllegalArgumentException("workSize: " + workSize);
        }

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(50);

        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount))
                .build();

        requestQueue = new LinkedBlockingDeque<>(queueSize);

        for (int i = 0; i < workSize; i++) {
            HttpWorker worker = new HttpWorker("http-worker-" + i);
            worker.start();
            workers.add(worker);
        }
    }

    public static HttpClient getInstance(int workSize, int queueSize, int retryCount) {
        return new PoolingHttpClient(workSize, queueSize, retryCount);
    }

    public static HttpClient getInstance(int workSize, int queueSize, int retryCount, RequestConfig requestConfig) {
        return new PoolingHttpClient(workSize, queueSize, retryCount, requestConfig);
    }

    @Override
    public void submit(HttpRequest request) throws InterruptedException {
        requestQueue.put(request);
        counter.increment();
    }

    @Override
    public void shutdown() {
        counter.waitUtilFinished();

        try {
            httpClient.close();
        } catch (IOException e) {
            HttpLogger.getLogger().error("[HttpClient] close error", e);
        }

        for (int i = 0; i < workers.size(); i++) {
            workers.get(i).close();
        }

        HttpLogger.getLogger().info("[HttpClient] close");
    }

    private class HttpWorker extends Thread {

        private volatile boolean runnable = true;

        public HttpWorker(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (runnable) {
                try {
                    HttpRequest request = requestQueue.take();
                    if (request != null) {
                        runWorker(request);
                    }
                } catch (InterruptedException e) {
                    HttpLogger.getLogger().info("[HttpClient] " + getName() + " close");
                }
            }
        }

        private void runWorker(HttpRequest request) {
            try {
                String result = httpClient.execute(request, new HttpResponseHandler());
                if (request.getCallback() != null) {
                    try {
                        request.getCallback().callback(result);
                    } catch (Exception e) {
                        HttpLogger.getLogger().error("[HttpClient] Response callback error", e);
                    }
                }
            } catch (Exception e) {
                if (request.getCallback() != null) {
                    try {
                        request.getCallback().callback("");
                    } catch (Exception other) {
                        HttpLogger.getLogger().error("[HttpClient] Response callback error", other);
                    }
                }
                HttpLogger.getLogger().error("[HttpClient] Request execute error", e);
            } finally {
                counter.decrement();
            }
        }

        private void close() {
            runnable = false;
            interrupt();
        }

    }

}
