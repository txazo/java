package org.txazo.jdk8.reflect;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 获取方法参数信息
 *
 * 编译选项添加-parameters才能获取到方法参数名称
 */
public class ParameterTest {

    @Test
    public void test() throws NoSuchMethodException {
        int i = 1;
        Method method = ParameterTest.class.getDeclaredMethod("test", int.class, String.class);
        for (Parameter parameter : method.getParameters()) {
            System.out.printf("Parameter %d: %s %s\n", i++, parameter.getType().getSimpleName(), parameter.getName());
        }
    }

    private void test(int id, String name) {
    }

}
