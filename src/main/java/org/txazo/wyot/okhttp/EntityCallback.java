package org.txazo.wyot.okhttp;

import java.io.IOException;

public interface EntityCallback<T> extends Callback<T> {

    void onSuccess(T t) throws IOException;

}
