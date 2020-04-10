package org.txazo.wyot.cat.transaction;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CatTransactionAspect {

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    private void match() {
    }

    @Around("match()")
    public Object catTransactionProcess(ProceedingJoinPoint point) throws Throwable {
        Transaction t = Cat.newTransaction("Transaction", getTransactionName(point));
        try {
            Object result = point.proceed();
            t.setStatus(Transaction.SUCCESS);
            return result;
        } catch (Throwable e) {
            t.setStatus(e);
            Cat.logError(e);
            throw e;
        } finally {
            t.complete();
        }
    }

    private static String getTransactionName(ProceedingJoinPoint point) {
        return point.getSignature().getDeclaringType().getSimpleName() + "." + point.getSignature().getName();
    }

}
