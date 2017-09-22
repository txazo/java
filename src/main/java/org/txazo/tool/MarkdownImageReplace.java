package org.txazo.tool;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownImageReplace {

    private static final Pattern IMAGE = Pattern.compile("!\\[(.*)\\]\\((.*)\\s+=(\\d+)x\\)");

    public static void main(String[] args) {
        replaceDir(new File("/Users/txazo/Txazoc/txazoc.github.io/_posts"));
        replaceDir(new File("/Users/txazo/Txazoc/txazoc.github.io/topic"));
    }

    private static void replaceDir(File dir) {
        File[] childs = dir.listFiles();
        if (ArrayUtils.isNotEmpty(childs)) {
            for (File child : childs) {
                if (child.isDirectory()) {
                    replaceDir(child);
                } else {
                    if (child.getName().endsWith(".md")) {
                        try {
                            replaceFile(child);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static void replaceFile(File file) throws Exception {
        List<String> lines = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                Matcher matcher = IMAGE.matcher(line);
                if (matcher.find()) {
                    line = String.format("<img src=\"%s\" style=\"width: %spx\" title=\"%s\" />", matcher.group(2), matcher.group(3), matcher.group(1));
                }
                lines.add(line);
            }
        } finally {
            br.close();
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } finally {
            bw.close();
        }
    }

}
