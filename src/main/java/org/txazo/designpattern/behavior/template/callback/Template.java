package org.txazo.designpattern.behavior.template.callback;

/**
 * 模板
 */
public class Template {

    public void templateMethod(TemplateCallback callback) {
        concreteMethod();
        callback.callback();
    }

    private void concreteMethod() {
        System.out.println("Template concreteMethod");
    }

}
