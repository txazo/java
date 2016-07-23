package org.txazo.java.io.io;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * File
 * <pre>
 * 1) 文件
 * 2) 实现依赖FileSystem(跨平台), Linux下实现类为UnixFileSystem
 * 3) 属性:
 *    FileSystem fs: 本地文件系统
 *    String path: 文件路径
 *    PathStatus status: 路径是否合法(不允许保护空格)
 *    int prefixLength: 前缀长度
 * </pre>
 */
public class FileTest {

    @Test
    public void test() {
        File file = new File("/Users/txazo");
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());

        /** 文件过滤, 回调模式, 策略模式 */
        file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return true;
            }

        });

        file.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return true;
            }

        });
    }

}
