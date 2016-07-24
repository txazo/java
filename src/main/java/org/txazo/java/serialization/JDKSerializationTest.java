package org.txazo.java.serialization;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.*;

public class JDKSerializationTest {

    @Test
    public void testJDKSerialization() throws Exception {
        Teacher[] teachers = new Teacher[2];
        teachers[0] = new Teacher(101, "chinese");
        teachers[1] = new Teacher(102, "english");
        Student student = new Student(1000, "xiaoming", teachers);
        ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("/Users/txazo/test/object.txt"));
        ois.writeObject(student);
    }

    @Test
    public void displayDate() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("/Users/txazo/test/object.txt"));
        int length = -1;
        byte[] temp = new byte[1024];
        while ((length = bis.read(temp)) != -1) {
            baos.write(temp, 0, length);
        }
        displayByHex(baos.toByteArray());
    }

    public static void displayByHex(byte[] data) {
        if (ArrayUtils.isNotEmpty(data)) {
            int length = 0;
            StringBuilder sb = new StringBuilder();
            String temp = null;
            for (byte b : data) {
                temp = Integer.toHexString(0xFF & b);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                sb.append(temp.toUpperCase()).append(" ");
                length += 1;
                if (length == 16) {
                    length = 0;
                    System.out.println(sb.toString());
                    sb = new StringBuilder();
                }
            }
            System.out.println(sb.toString());
        }
    }

}
