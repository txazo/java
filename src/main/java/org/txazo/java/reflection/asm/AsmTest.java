package org.txazo.java.reflection.asm;

import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class AsmTest {

    @Test
    public void testAsm() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT,
                "org/txazo/reflection/asm/AbstractASM", null, "java/lang/Object", new String[]{"org/txazo/reflection/asm/ASM"});
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "id", "I", null, 1).visitEnd();
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "matchId", "(I)Z", null, null).visitEnd();
        byte[] data = cw.toByteArray();

        try (OutputStream os = new FileOutputStream("AbstractASM.class")) {
            os.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
