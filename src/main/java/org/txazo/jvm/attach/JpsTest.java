package org.txazo.jvm.attach;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * 列出系统的虚拟机进程, 类似jps
 */
public class JpsTest {

    public static void main(String[] args) {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : vms) {
            System.out.println(vmd.id() + " " + vmd.displayName());
        }
    }

}
