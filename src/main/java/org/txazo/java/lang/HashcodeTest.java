package org.txazo.java.lang;

/**
 * Object.hashcode()
 * <p/>
 * 1) hashcode()用在HashMap HashSet Hashtable等散列表的数据结构中
 */
public class HashcodeTest {

    /**
     * 未重写hashcode()
     */
    private class NotOverrideHashcode {

        protected int id;
        protected String name;

        public NotOverrideHashcode(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * 重写hashcode()
     */
    private class OverrideHashcode extends NotOverrideHashcode {

        public OverrideHashcode(int id, String name) {
            super(id, name);
        }

    }

}
