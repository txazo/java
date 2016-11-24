package org.txazo.jmx.mbean.standard;

public class Client implements ClientMBean {

    private String type;

    public Client() {
    }

    public Client(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void operation() {
        System.out.println("Client operation ...");
    }

}
