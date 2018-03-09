package org.txazo.java.io.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 临时文件
 */
public class TempFileTest {

    @Test
    public void test() throws IOException {
        File tempFile = File.createTempFile("data_", ".tmp", new File("/tmp/java"));
        System.out.println(String.format("TempFilePath: %s", tempFile.getAbsolutePath()));
        Assert.assertTrue(tempFile.exists());
        // 虚拟机退出时删除
        tempFile.deleteOnExit();
    }

}
