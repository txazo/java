package org.txazo.java.io.serializable;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 序列化/反序列化性能
 * <p>
 * 1) 序列化时间
 * 2) 反序列化时间
 * 3) 序列化格式: json
 * 4) 序列化大小
 * 5) 序列化压缩大小
 */
public class FastjsonTest {

    @Test
    public void testSerializable() {
        List<Entity> entitys = new ArrayList<Entity>();
        entitys.add(new Entity((byte) 92, 'c', true, (short) 1, 2, 3, 1.0f, 2.0d, "str", new String[]{"as"}));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("entitys", entitys);

        String json = JSON.toJSONString(map);
        System.out.println(JSON.toJSONString(map));

        JSON.parse(json);
    }

}
