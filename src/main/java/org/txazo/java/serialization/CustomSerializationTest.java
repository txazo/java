package org.txazo.java.serialization;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class CustomSerializationTest {

    @Test
    public void test() throws Exception {
        Entity entity = new Entity(1, "root");
        String fileName = "/Users/txazo/test/object.txt";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(entity);
        IOUtils.closeQuietly(oos);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Entity readEntity = (Entity) ois.readObject();
        IOUtils.closeQuietly(ois);
        Assert.assertEquals(entity.getId(), readEntity.getId());
        Assert.assertEquals(entity.getName(), readEntity.getName());
    }

    private static class Entity implements Serializable {

        private static final long serialVersionUID = -9154888199217590673L;

        private int id;
        private String name;

        public Entity(int id, String name) {
            this.id = id;
            this.name = name;
        }

        private void readObject(ObjectInputStream in) throws IOException {
            id = in.readInt();
            name = in.readUTF();
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeInt(id);
            out.writeUTF(name);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

}
