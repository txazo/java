package org.txazo.java.io.io;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

/**
 * FileFilter
 * <pre>
 * 1) 文件过滤
 * 2) File.listFiles(FileFilter filter)
 * </pre>
 */
public class FileFilterTest {

    @Test
    public void test() {
        File parent = new File("/Users/txazo");
        /** 回调模式 */
        File[] childs = parent.listFiles(new DefaultFileFilter());
        if (ArrayUtils.isNotEmpty(childs)) {
            for (File child : childs) {
                System.out.println(child.getName());
            }
        }
    }

    private class DefaultFileFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return "Downloads".equalsIgnoreCase(pathname.getName());
        }

    }

}
