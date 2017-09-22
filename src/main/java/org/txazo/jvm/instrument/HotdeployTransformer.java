package org.txazo.jvm.instrument;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 热部署
 *
 * @author xztu
 * @date 2017-09-19
 */
public class HotdeployTransformer implements ClassFileTransformer {

    /**
     * 1. 不支持add/remove field
     * 2. 不支持add/delete method
     * 3. 不支持改变父类和接口
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("[DynamicBytecodeAgent] transform ...");

        String classPath = "/" + className + ".class";
        InputStream in = HotdeployTransformer.class.getResourceAsStream(classPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            int length = -1;
            byte[] temp = new byte[1024];
            while ((length = in.read(temp)) != -1) {
                baos.write(temp, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

}
