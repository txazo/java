package org.txazo.wyot.cat.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContext {

    private static String APP_NAME;

    @Value("${spring.application.name}")
    public void setAppName(String appName) {
        APP_NAME = appName;
    }

    public static String getAppName() {
        return APP_NAME != null ? APP_NAME : "unknown";
    }

}
