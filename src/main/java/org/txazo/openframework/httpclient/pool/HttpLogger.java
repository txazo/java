package org.txazo.openframework.httpclient.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpLogger.class);

    public static Logger getLogger() {
        return LOGGER;
    }

}
