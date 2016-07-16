package org.txazo.designpattern.structural.composite;

import org.junit.Test;

/**
 * 组合模式
 */
public class CompositeTest {

    @Test
    public void test() {
        Component college = new Composite("软件学院");

        Component class0901 = new Composite("0901班");
        class0901.add(new Leaf("刘晓"));
        class0901.add(new Leaf("张亮"));
        class0901.add(new Leaf("徐勇"));

        Component class0902 = new Composite("0901班");
        class0902.add(new Leaf("余丽"));
        class0902.add(new Leaf("占浩"));
        class0902.add(new Leaf("涂小"));

        Component assistant = new Composite("教职工");
        assistant.add(new Leaf("李姬"));
        assistant.add(new Leaf("林琪"));
        assistant.add(new Leaf("范蕾"));

        college.add(class0901);
        college.add(class0902);
        college.add(assistant);

        college.notify("国庆节放假7天");

        college.remove(class0902);
        college.remove(assistant);
        college.notify("0901班全体晚上7点到学院集合");

        college.add(class0902);
        college.add(assistant);
        college.notify("国庆节放假7天改为5天");
    }

}
