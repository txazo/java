import org.txazo.tool.http.PooledHttpClient;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentRequest {

    private static final List<String> URLS = new ArrayList<>();

    static {
        URLS.add("http://beta.mobile.dp/apireader/executeApi?url=http%3A%2F%2F172.24.38.62%3A8080%2Fdiscovery%2Ffindcontent.bin%3Fcityid%3D1&headers=%7B%22pragma-dpid%22%3A%227777777%22%2C%22User-Agent%22%3A%22MApi+1.1+(dpscope+9.1.0+appstore%3B+iPhone+6.1.3+iPhone4%2C1%3B+a0d0)%22%7D");
        URLS.add("http://beta.mobile.dp/apireader/executeApi?url=http%3A%2F%2F172.24.38.62%3A8080%2Fdiscovery%2Fcontentrecommendnav.bin%3Fcityid%3D1&headers=%7B%22User-Agent%22%3A%22MApi+1.1+(dpscope+9.1.0+appstore%3B+iPhone+6.1.3+iPhone4%2C1%3B+a0d0)%22%7D");
        URLS.add("http://beta.mobile.dp/apireader/executeApi?url=http%3A%2F%2F172.24.38.62%3A8080%2Fdiscovery%2Fcontentrecommend.bin%3Fcityid%3D1&headers=%7B%22User-Agent%22%3A%22MApi+1.1+(dpscope+9.1.0+appstore%3B+iPhone+6.1.3+iPhone4%2C1%3B+a0d0)%22%7D");
        URLS.add("http://beta.mobile.dp/apireader/executeApi?url=http%3A%2F%2F172.24.38.62%3A8080%2Fdiscovery%2Fcontentoperation.bin%3Fcityid%3D1&headers=%7B%22User-Agent%22%3A%22MApi+1.1+(dpscope+9.1.0+appstore%3B+iPhone+6.1.3+iPhone4%2C1%3B+a0d0)%22%7D");
        URLS.add("http://beta.mobile.dp/apireader/executeApi?url=http%3A%2F%2F172.24.38.62%3A8080%2Fdiscovery%2Fdpheadline.bin%3Fcityid%3D1&headers=%7B%22User-Agent%22%3A%22MApi+1.1+(dpscope+9.1.0+appstore%3B+iPhone+6.1.3+iPhone4%2C1%3B+a0d0)%22%7D");
    }

    public static void main(String[] args) {
        PooledHttpClient httpClient = PooledHttpClient.getInstance(50, 500);
        int index = 0;
        while (true) {
            httpClient.get(URLS.get(index++ % URLS.size()));

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
