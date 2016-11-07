package org.txazo.btrace;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class HelloWorld {

    @OnMethod(clazz = "java.lang.Thread", method = "start")
    public static void onThreadStart() {
        println("thread start!");
    }

}
