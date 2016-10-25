package org.txazo.jvm.hsdb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HsdbTest {

    public static void main(String[] args) throws IOException {
        GroupList groupList = new GroupList();
        List<Group> groups = new ArrayList<Group>();
        groups.add(new Group(1, "group 1"));
        groups.add(new Group(2, "group 2"));
        groupList.setGroups(groups);

        System.in.read();
    }

}
