package org.txazo;

import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.txazo.wyot.okhttp.Callback;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.*;

public class A {

    private static final String GET = "GET";
    private static final String POST = "POST";
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient httpClient;

    public static void main(String[] args) {
        httpClient = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .dispatcher(new Dispatcher(new ThreadPoolExecutor(20, 50,
                        60, TimeUnit.SECONDS, new LinkedBlockingQueue<>())))
                .build();
        httpClient.dispatcher().setMaxRequests(1000);
        httpClient.dispatcher().setMaxRequestsPerHost(100);

        ExecutorService pool = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 999; i++) {
            final int j = i;
            pool.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        String domain = String.format("%03d", j);
                        String url = "http://www." + domain + ".com";
                        System.out.println(url);
                        String result = get(url);
                        FileUtils.writeStringToFile(new File("/Users/txazo/test/" + domain + ".html"), result, "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }

        pool.shutdown();
    }

    public static String get(String url) throws IOException {
        return execute(GET, url, null, null);
    }

    private static String execute(String method, String url, Map<String, String> headers, RequestBody requestBody) throws IOException {
        return httpClient.newCall(buildRequest(method, url, headers, requestBody)).execute().body().string();
    }

    private static Request buildRequest(String method, String url, Map<String, String> headers, RequestBody requestBody) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .method(method, requestBody);
        if (headers != null && headers.size() > 0) {
            headers.forEach((key, value) -> requestBuilder.addHeader(key, value));
        }
        return requestBuilder.build();
    }

    private static RequestBody buildFormRequestBody(Map<String, String> params) {
        if (params == null || params.size() <= 0) {
            return null;
        }
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        params.forEach((key, value) -> formBodyBuilder.add(key, value));
        return formBodyBuilder.build();
    }

    private <T> Class<T> getInterfaceGenericType(Callback<T> callback) throws Exception {
        Type type = ((ParameterizedType) callback.getClass().getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
        Field field = type.getClass().getDeclaredField("rawType");
        field.setAccessible(true);
        return (Class<T>) field.get(type);
    }

}
