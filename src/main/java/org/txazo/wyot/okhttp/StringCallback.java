package org.txazo.wyot.okhttp;

import java.io.IOException;

public interface StringCallback extends Callback {

    void onSuccess(String value) throws IOException;

}
