package org.txazo.jvm.gc;

import org.txazo.utils.MemoryUtils;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

/**
 * Full GC
 */
public class FullGCTest {

    /**
     * VM Args: -server -Xms200m -Xmx200m -XX:NewRatio=1 -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=3 -XX:PretenureSizeThreshold=20971520 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps  -XX:+PrintGCTimeStamps
     */
    public static void main(String[] args) throws Exception {
        List<Entity> entities = new ArrayList<>();
        List<Reference> weakReferences = new ArrayList<>();
        for (; ; ) {
            new Entity(1);
            entities.add(new Entity(1));
            Thread.sleep(100);
            MemoryUtils.printHeapMemoryDetail();
        }
    }

    private static class Entity {

        private byte[] b;

        public Entity(int size) {
            this.b = new byte[1024 * 1024 * size];
        }

    }

}
