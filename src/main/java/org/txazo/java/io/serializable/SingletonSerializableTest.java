package org.txazo.java.io.serializable;

import java.io.*;

/**
 * 单例序列化/反序列化
 */
public class SingletonSerializableTest implements Serializable {

    private static final long serialVersionUID = 2088946917458269122L;

    private static final SingletonSerializableTest instance = new SingletonSerializableTest();

    private SingletonSerializableTest() {
        if (instance != null) {
            // 避免通过反射创建
            throw new UnsupportedOperationException();
        }
    }

    // 反序列化
    public Object readResolve() throws ObjectStreamException {
        return instance;
    }

    // 序列化
    public Object writeReplace() throws ObjectStreamException {
        return instance;
    }

    // 克隆
    @Override
    public SingletonSerializableTest clone() throws CloneNotSupportedException {
        return instance;
    }

    public static SingletonSerializableTest getInstance() {
        return instance;
    }

    public static void main(String[] args) throws Exception {
        // 序列化/反序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(SingletonSerializableTest.getInstance());
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        SingletonSerializableTest instance = (SingletonSerializableTest) ois.readObject();
        System.out.println(instance == SingletonSerializableTest.getInstance());

        // 克隆
        SingletonSerializableTest clone = SingletonSerializableTest.getInstance().clone();
        System.out.println(clone == SingletonSerializableTest.getInstance());

        SingletonSerializableTest reflect = SingletonSerializableTest.class.newInstance();
        System.out.println(reflect == SingletonSerializableTest.getInstance());
    }

}
