package org.txazo.java.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {

    private static final String URL = "http://appkit.dper.com/elecontent/cus/addContent.html";
    private static final Map<String, Integer> cellFields = new HashMap<>();
    private static final Map<String, String> tagMap = new HashMap<>();
    private static final Map<String, String> typeMap = new HashMap<>();

    static {
        cellFields.put("tasteTag", 3);
        cellFields.put("originName", 0);
        cellFields.put("typeName", 1);
        cellFields.put("tasteTopicId", 2);
        cellFields.put("hasTasteType", 4);
        cellFields.put("tasteSubTitle", 5);
        cellFields.put("tasteListTitle", 6);
        cellFields.put("tasteTopicTitle", 7);
        cellFields.put("imageName", 8);

        tagMap.put("分类", "1");
        tagMap.put("推荐菜", "2");
        tagMap.put("场景", "3");

        typeMap.put("探新店", "1814");
        typeMap.put("推荐菜", "1815");
        typeMap.put("好去处", "1816");
    }

    public static void main(String[] args) throws Exception {
        int startRow = 2;
        int endRow = 49;
        Workbook wb = WorkbookFactory.create(new FileInputStream("/Users/txazo/Desktop/data_05-13.xlsx"));
        Sheet sheet = wb.getSheetAt(0);

        List<Map<String, String>> list = new ArrayList<>();

        for (int i = startRow; i <= endRow; i++) {
            list.add(getContent(sheet.getRow(i)));
        }

        for (Map<String, String> params : list) {
            post(URL, params);
        }

        wb.close();
    }

    private static Map<String, String> getContent(Row row) {
        Map<String, String> data = new HashMap<>();
        for (Map.Entry<String, Integer> entry : cellFields.entrySet()) {
            data.put(entry.getKey(), getCellValue(row, entry.getValue()));
            if (entry.getKey().equals("tasteTag")) {
                data.put(entry.getKey(), tagMap.get(data.get(entry.getKey())));
            }
            if (entry.getKey().equals("hasTasteType")) {
                data.put("eleContent.eleId", typeMap.get(data.get(entry.getKey())));
            }
        }

        String imageName = data.get("imageName");
        data.put("tasteListImage", buildImageUrl(imageName, data.get("tasteTag").equals("2") ? "216-136" : "335-170"));
        data.put("tasteTopicImage", buildImageUrl(imageName, "750-260"));
        data.put("tasteCombImage", buildImageUrl(imageName, "(noword)164-164"));
        data.put("tasteIndexImage", buildImageUrl(imageName, "164-164"));

        data.remove("imageName");
        data.remove("hasTasteType");

        return data;
    }

    private static String buildImageUrl(String imageName, String size) {
        return "http://www.dpfile.com/sc/find/taste/images/" + imageName + size + ".jpg";
    }

    private static String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return StringUtils.EMPTY;
        }
        return cell.getStringCellValue().trim();
    }

    private static void post(String url, Map<String, String> params) throws Exception {
        params.put("eleScene", "findhastaste");
        params.put("eleContent.contentId", "0");
        params.put("city", "1");
        params.put("display", "1");

        System.out.println(params.get("typeName"));

        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Host", "appkit.dper.com");
        connection.setRequestProperty("Content-Length", String.valueOf(sb.toString().length()));
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("Cookie", "_ga=GA1.2.1214698367.1463108829; JSESSIONID=C314BF566BD6E33424D50CE3C22EC934; ticket=AAFSsPYAkNKN6Mb0Q6Li8D8gawrtLA+SLU9A+ZU4ixTU0AodF17k0+xC");

        connection.connect();

        OutputStream os = connection.getOutputStream();
        os.write(sb.toString().getBytes());
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();

        connection.disconnect();
    }

}
