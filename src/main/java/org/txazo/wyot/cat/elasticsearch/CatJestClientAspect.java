package org.txazo.wyot.cat.elasticsearch;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.google.gson.GsonBuilder;
import io.searchbox.action.Action;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CatJestClientAspect {

    @Pointcut("target(io.searchbox.client.JestClient)")
    private void match() {
    }

    @Around("match()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        if ("execute".equals(point.getSignature().getName())) {
            Action action = (Action) point.getArgs()[0];
            Transaction t = Cat.newTransaction("ES", action.getURI());
            try {
                String data = action.getData(new GsonBuilder().serializeNulls().create());
                if (data != null) {
                    data = data.replaceAll("\n|\\s", "");
                }
                Cat.logEvent("ES.Method", action.getRestMethodName(), Message.SUCCESS, data);

                Object ret = point.proceed();
                t.setStatus(Transaction.SUCCESS);
                return ret;
            } catch (Exception e) {
                t.setStatus(e);
                Cat.logError(e);
                throw e;
            } finally {
                t.complete();
            }
        } else {
            return point.proceed();
        }
    }

}
