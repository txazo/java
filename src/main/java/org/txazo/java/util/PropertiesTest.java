package org.txazo.java.util;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 系统属性
 * <p>
 * 1. 继承自Hashtable
 *
 * @see java.util.Properties
 */
public class PropertiesTest {

    @Test
    public void testSystemProperties() {
        // 系统属性
        showProperties(System.getProperties());
    }

    @Test
    public void testLoadProperties() throws IOException {
        Properties prop = new Properties();
        try (InputStream is = PropertiesTest.class.getResourceAsStream("/config.properties")) {
            // 读取属性
            prop.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 设置属性
        prop.setProperty("datetime", String.valueOf(System.currentTimeMillis()));

        showProperties(prop);

        try (OutputStream os = new FileOutputStream(PropertiesTest.class.getResource("/config.properties").getFile())) {
            // 写入属性到文件
            prop.store(os, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProperties(Properties prop) {
        String key = null;
        // 属性遍历
        for (Enumeration<?> e = prop.propertyNames(); e.hasMoreElements(); ) {
            key = (String) e.nextElement();
            System.out.println(key + " = " + prop.getProperty(key));
        }
    }

}
