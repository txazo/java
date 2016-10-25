import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HasTasteTest {

    private static final String CITYS[] = "1,2,3,4,5,6,7,8,9,10,11,13,14,15,16,17,18,19,21,22,79,110,160,344".split(",");

    private static final Map<String, String> RESULT = new HashMap<>();

    public static void main(String[] args) throws Exception {
        for (String cityId : CITYS) {
            URL url = new URL("http://m.dper.com/apireader/executeApi?url=http%3A%2F%2Fm.api.dianping.com%2Fdiscovery%2Ffindtopiclist.bin%3Fcityid%3D" + cityId + "%26type%3D0&headers=%7B%22pragma-dpid%22%3A%227777777%22%2C%22User-Agent%22%3A%22MApi+1.1+(dpscope+9.0.6+appstore%3B+iPhone+6.1.3+iPhone4%2C1%3B+a0d0)%22%7D");
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(url.openStream()));

                JSONObject jsonObject = JSON.parseObject(br.readLine());
                JSONObject result = (JSONObject) jsonObject.get("result");
                JSONArray list = (JSONArray) result.get("List");
                for (Object obj : list) {
                    JSONObject taste = (JSONObject) obj;
                    addResult(taste.getString("Url"), cityId);
                }
            } finally {
                IOUtils.closeQuietly(br);
            }
        }

        for (String key : RESULT.keySet()) {
            String value = RESULT.get(key);
            System.out.println(key + "\t\t" + value.substring(0, value.length() - 1));
        }
    }

    private static void addResult(String url, String cityId) {
        if (RESULT.containsKey(url)) {
            RESULT.put(url, RESULT.get(url) + cityId + ",");
        } else {
            RESULT.put(url, cityId + ",");
        }
    }

}
