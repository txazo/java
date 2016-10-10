import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.util.Iterator;

public class ImageCutTest {

    public static void main(String[] args) throws Exception {
        resize(new File("/Users/txazo/Desktop/jhat.png"), new File("/Users/txazo/Desktop/jhat_new.jpg"), 540, 1.0f);

//        int width = 230;
//        String sourcePath = "/Users/txazo/Desktop/source";
//        String tempPath = "/Users/txazo/Desktop/temp";
//        String destPath = "/Users/txazo/Desktop/dest";
//        File destFile = new File(destPath);
//        File tempFile = new File(tempPath);
//        FileUtils.deleteDirectory(destFile);
//        FileUtils.deleteDirectory(tempFile);
//        destFile.mkdir();
//        tempFile.mkdir();
//        File sourceFile = new File(sourcePath);
//        File[] childs = sourceFile.listFiles();
//        if (ArrayUtils.isNotEmpty(childs)) {
//            for (File child : childs) {
//                if (child.isFile() && !child.getName().equals(".DS_Store")) {
//                    resize(child, new File(tempPath + "/" + child.getName()), width, 1f);
//                }
//            }
//        }
//
//        childs = tempFile.listFiles();
//        if (ArrayUtils.isNotEmpty(childs)) {
//            int i = 1;
//            for (File child : childs) {
//                if (child.isFile() && !child.getName().equals(".DS_Store")) {
//                    cutImage(child, width, width, destPath + "/rankicon_" + i++ + ".jpg");
//                }
//            }
//        }
    }

    private static void cutImage(File source, int width, int height, String destFile) {
        InputStream input = null;
        ImageInputStream iis = null;
        try {
            input = new FileInputStream(source);
            Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = iterator.next();
            iis = ImageIO.createImageInputStream(input);
            reader.setInput(iis, true);

            int w = reader.getWidth(0);
            int h = reader.getHeight(0);

            if (w >= width && h >= height) {
                ImageReadParam param = reader.getDefaultReadParam();
                Rectangle rect = new Rectangle((w - width) / 2, (h - height) / 2, width, height);
                param.setSourceRegion(rect);
                BufferedImage bi = reader.read(0, param);
                ImageIO.write(bi, "jpg", new File(destFile));
                System.out.println("cutImage success with image " + source);
            } else {
                System.err.println("cutImage error, pixel is too small with image " + source);
            }
        } catch (Exception e) {
            System.err.println("cutImage error");
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (iis != null) {
                try {
                    iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void resize(File originalFile, File resizedFile, int newWidth, float quality) throws IOException {

        if (quality > 1) {
            throw new IllegalArgumentException(
                    "Quality has to be between 0 and 1");
        }

        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null;

        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null);

//        if (iWidth > iHeight) {
//            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,
//                    newWidth, Image.SCALE_SMOOTH);
//        } else {
//            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
//                    / iWidth, Image.SCALE_SMOOTH);
//        }

        resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
                / iWidth, Image.SCALE_SMOOTH);

        Image temp = new ImageIcon(resizedImage).getImage();

        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.createGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        float softenFactor = 0.05f;
        float[] softenArray = {0, softenFactor, 0, softenFactor,
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0};
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);

        FileOutputStream out = new FileOutputStream(resizedFile);

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

        JPEGEncodeParam param = encoder
                .getDefaultJPEGEncodeParam(bufferedImage);

        param.setQuality(quality, true);

        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);
    }

}
