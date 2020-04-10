package org.txazo.wyot.cat.springcloud;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.txazo.wyot.cat.common.ApplicationContext;
import org.txazo.wyot.cat.common.CatConstant;
import org.txazo.wyot.cat.common.CatContext;

public class SpringCloudClientInterceptor implements RequestInterceptor {

    // 是否开启Hystrix
    private boolean enableHystrix;

    public SpringCloudClientInterceptor(boolean enableHystrix) {
        this.enableHystrix = enableHystrix;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (enableHystrix) {
            buildCatLogAndMessageWithHystrix(template);
        } else {
            buildCatLogAndMessage(template);
        }
    }

    private static void buildCatLogAndMessage(RequestTemplate template) {
        try {
            Cat.logEvent("SOAClient", template.url());
            Cat.logEvent("SOAClient.Method", template.method(), Transaction.SUCCESS, template.method().equals("GET") ? getQueryString(template) : new String(template.body(), "UTF-8"));
            Cat.logTrace("SOAClient.System", ApplicationContext.getAppName());

            Cat.Context context = new CatContext();
            Cat.logRemoteCallClient(context);
            template.header(CatConstant.REMOTE_CALL_FLAG, "true");
            template.header(CatConstant.REMOTE_CALL_APP, ApplicationContext.getAppName());
            template.header(Cat.Context.ROOT, context.getProperty(Cat.Context.ROOT));
            template.header(Cat.Context.PARENT, context.getProperty(Cat.Context.PARENT));
            template.header(Cat.Context.CHILD, context.getProperty(Cat.Context.CHILD));
        } catch (Exception e) {
            Cat.logError(e);
        }
    }

    private static void buildCatLogAndMessageWithHystrix(RequestTemplate template) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        template.header(CatConstant.REMOTE_CALL_FLAG, requestAttributes.getAttribute(CatConstant.REMOTE_CALL_FLAG, 0).toString());
        template.header(CatConstant.REMOTE_CALL_APP, requestAttributes.getAttribute(CatConstant.REMOTE_CALL_APP, 0).toString());
        template.header(Cat.Context.ROOT, requestAttributes.getAttribute(Cat.Context.ROOT, 0).toString());
        template.header(Cat.Context.PARENT, requestAttributes.getAttribute(Cat.Context.PARENT, 0).toString());
        template.header(Cat.Context.CHILD, requestAttributes.getAttribute(Cat.Context.CHILD, 0).toString());
    }

    private static String getQueryString(RequestTemplate template) {
        String query = template.queryLine();
        if (query != null && query.startsWith("?")) {
            return query.substring(1);
        }
        return query;
    }

}
