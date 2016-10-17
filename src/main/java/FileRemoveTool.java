import org.apache.commons.lang3.ArrayUtils;

import java.io.File;

public class FileRemoveTool {

    private static final String[] SUFFIX = {".h", ".c", ".hpp", ".cpp"};

    public static void main(String[] args) {
        removeFile(new File("/Users/txazo/TxazoProject/jdk7u"));
    }

    public static void removeFile(File parent) {
        File[] childs = parent.listFiles();
        if (ArrayUtils.isNotEmpty(childs)) {
            for (File child : childs) {
                if (child.isDirectory()) {
                    removeFile(child);
                } else if (!match(child.getName())) {
                    deleteFile(child);
                }
            }
        }

        childs = parent.listFiles();
        if (ArrayUtils.isEmpty(childs)) {
            deleteFile(parent);
        }
    }

    public static boolean match(String fileName) {
        for (String s : SUFFIX) {
            if (fileName.endsWith(s)) {
                return true;
            }
        }
        return false;
    }

    private static void deleteFile(File file) {
        file.delete();
        System.out.println("[delete] " + file.getAbsolutePath());
    }

}
