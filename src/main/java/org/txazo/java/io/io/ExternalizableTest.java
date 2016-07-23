package org.txazo.java.io.io;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.txazo.java.io.io.bean.Entity;

import java.io.*;

/**
 * Externalizable
 * <pre>
 * 1) 控制序列化和反序列化过程
 * 2) 继承自java.io.Serializable
 * 3) Externalizable反序列化时会调用默认构造函数
 * </pre>
 */
public class ExternalizableTest {

    @Test
    public void test() {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(new Entity(1, "Externalizable"));
            ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            Entity entity = (Entity) ois.readObject();
            Assert.assertEquals(1, entity.getId());
            Assert.assertEquals("Externalizable", entity.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(oos);
            IOUtils.closeQuietly(baos);
        }
    }

}
