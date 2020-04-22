package org.txazo.wyot.cat.springcloud;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.txazo.wyot.cat.common.ApplicationContext;
import org.txazo.wyot.cat.common.CatConstant;
import org.txazo.wyot.cat.common.CatContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringCloudServerInterceptor implements HandlerInterceptor {

    private ThreadLocal<Transaction> transactionThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if ("true".equals(request.getHeader(CatConstant.REMOTE_CALL_FLAG))) {
            Transaction t = Cat.newTransaction("SOAServer", request.getRequestURI());
            transactionThreadLocal.set(t);

            Cat.logEvent("SOAServer.Method", request.getMethod());
            Cat.logEvent("SOAServer.Remote", request.getHeader(CatConstant.REMOTE_CALL_APP), Message.SUCCESS, request.getRemoteHost());
            Cat.logTrace("SOAServer.System", ApplicationContext.getAppName());

            buildCallMessageId(request);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        Transaction t = transactionThreadLocal.get();
        if (t != null) {
            if (e != null) {
                t.setStatus(e);
                Cat.logError(e);
            } else {
                t.setStatus(Transaction.SUCCESS);
            }
            t.complete();
            transactionThreadLocal.remove();
        }
    }

    private static void buildCallMessageId(HttpServletRequest request) {
        CatContext context = new CatContext();
        context.addProperty(Cat.Context.ROOT, request.getHeader(Cat.Context.ROOT));
        context.addProperty(Cat.Context.PARENT, request.getHeader(Cat.Context.PARENT));
        context.addProperty(Cat.Context.CHILD, request.getHeader(Cat.Context.CHILD));
        Cat.logRemoteCallServer(context);
    }

}
