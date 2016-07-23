package org.txazo.java.collection.map;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map
 */
public class MapTest {

    @Test
    public void test() {
        Map<Object, Object> map = new HashMap<Object, Object>();

        /** key集合 - key遍历 */
        Set<Object> keySet = map.keySet();
        /** value集合 - value遍历 */
        Collection<Object> valueSet = map.values();
        /** entry集合 - entry遍历 */
        Set<Map.Entry<Object, Object>> entrySet = map.entrySet();
    }

}
