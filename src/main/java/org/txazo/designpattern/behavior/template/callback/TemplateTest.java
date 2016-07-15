package org.txazo.designpattern.behavior.template.callback;

import org.junit.Test;

public class TemplateTest {

    @Test
    public void test() {
        Template template = new Template();
        template.templateMethod(new TemplateCallback() {

            @Override
            public void callback() {
                System.out.println("TemplateCallback callback");
            }

        });
    }

}
