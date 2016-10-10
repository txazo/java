import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JstatTest {

    private static ScheduledExecutorService pool = Executors.newScheduledThreadPool(50);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            pool.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        request();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 1000, TimeUnit.MILLISECONDS);
        }
        System.in.read();
    }

    private static void request() throws Exception {
        URL url = new URL("http://beta.mobile.dp/apireader/executeApi?url=http%3A%2F%2F172.24.38.141%3A8080%2Fdiscovery%2Fcontentrecommend.bin%3Fcityid%3D1&headers=%7B%22User-Agent%22%3A%22MApi+1.1+(dpscope+9.0.4+appstore%3B+iPhone+6.1.3+iPhone4%2C1%3B+a0d0)%22%7D");
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
