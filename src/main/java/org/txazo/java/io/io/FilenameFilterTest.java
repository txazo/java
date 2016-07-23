package org.txazo.java.io.io;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * FilenameFilter
 * <pre>
 * 1) 文件名过滤
 * 2) File.list(FilenameFilter filter)
 * 3) File.listFiles(FilenameFilter filter)
 * <pre/>
 */
public class FilenameFilterTest {

    @Test
    public void test() {
        File parent = new File("/Users/txazo");
        FilenameFilter filter = new DefaultFilenameFilter();
        /** 回调模式 */
        String[] childNames = parent.list(filter);
        File[] childs = parent.listFiles(filter);
        if (ArrayUtils.isNotEmpty(childNames)) {
            for (int i = 0; i < childNames.length; i++) {
                Assert.assertEquals(childNames[i], childs[i].getName());
            }
        }
    }

    private class DefaultFilenameFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return dir.getAbsolutePath().equalsIgnoreCase("/Users/txazo") && "Downloads".equalsIgnoreCase(name);
        }

    }

}
