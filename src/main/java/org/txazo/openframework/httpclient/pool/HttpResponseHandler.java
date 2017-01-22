package org.txazo.openframework.httpclient.pool;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpResponseHandler implements ResponseHandler<String> {

    @Override
    public String handleResponse(HttpResponse response) throws IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new ResponseEmptyException("Empty response entity");
            }
            return EntityUtils.toString(entity);
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    }

}
