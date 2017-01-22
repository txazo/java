package org.txazo.openframework.httpclient.pool;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequest extends HttpPost {

    private HttpResponseCallback callback;

    public HttpRequest(String url) throws UnsupportedEncodingException {
        super(url);
    }

    public HttpRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        super(url);
        setEntity(buildFormEntity(params));
    }

    private static HttpEntity buildFormEntity(Map<String, Object> params) throws UnsupportedEncodingException {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        if (MapUtils.isNotEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (StringUtils.isBlank(entry.getKey())) {
                    continue;
                }
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString()));
            }
        }
        return new UrlEncodedFormEntity(formParams, "UTF-8");
    }

    public void setFileEntity(Map<String, Object> params, String fileName, File file) throws FileNotFoundException {
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (MapUtils.isNotEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                entityBuilder.addPart(entry.getKey(), new StringBody(entry.getValue() == null ? StringUtils.EMPTY : entry.getValue().toString(), ContentType.create("text/plain", "UTF-8")));
            }
        }
        entityBuilder.addPart(fileName, new FileBody(file, ContentType.DEFAULT_BINARY));
        setEntity(entityBuilder.build());
    }

    public HttpResponseCallback getCallback() {
        return callback;
    }

    public void setCallback(HttpResponseCallback callback) {
        this.callback = callback;
    }

}
