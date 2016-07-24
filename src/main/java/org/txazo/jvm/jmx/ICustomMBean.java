package org.txazo.jvm.jmx;

public class ICustomMBean implements IMBean {

    private String mBeanName;

    public ICustomMBean(String mBeanName) {
        this.mBeanName = mBeanName;
    }

    @Override
    public String getMBeanName() {
        return mBeanName;
    }

}
