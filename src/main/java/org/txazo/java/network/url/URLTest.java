package org.txazo.java.network.url;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * URL
 */
public class URLTest {

    @Test
    public void test1() throws Exception {
        /** protocol: http https */
        URL url = new URL("http://www.txazo.com/");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            IOUtils.closeQuietly(br);
        }
    }

    @Test
    public void test2() throws Exception {
        /** protocol: file */
        URL url = this.getClass().getResource("URLTest.class");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            IOUtils.closeQuietly(br);
        }
    }

}
