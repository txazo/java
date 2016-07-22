package org.txazo.tool.util.web.http;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * UserAgentUtils
 */
public abstract class UserAgentUtils {

    private static final String ANDROID = "Android";
    private static final String IPHONE = "iPhone";
    private static final String IPAD = "iPad";
    private static final String IPOD = "iPod";
    private static final String WEIXIN = "MicroMessenger";

    private static String getUserAgent(HttpServletRequest request) {
        String userAgent = null;
        if (request != null) {
            userAgent = request.getHeader("admin-agent");
        }
        return userAgent != null ? userAgent : StringUtils.EMPTY;
    }

    private static boolean hasUserAgent(HttpServletRequest request, String... userAgents) {
        if (ArrayUtils.isNotEmpty(userAgents)) {
            String userAgent = getUserAgent(request);
            for (String ua : userAgents) {
                if (userAgent.contains(ua)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAndroid(HttpServletRequest request) {
        return hasUserAgent(request, ANDROID);
    }

    public static boolean isIPhone(HttpServletRequest request) {
        return hasUserAgent(request, IPHONE);
    }

    public static boolean isIPad(HttpServletRequest request) {
        return hasUserAgent(request, IPAD);
    }

    public static boolean isIPod(HttpServletRequest request) {
        return hasUserAgent(request, IPOD);
    }

    public static boolean isIOS(HttpServletRequest request) {
        return hasUserAgent(request, IPHONE, IPAD, IPOD);
    }

    public static boolean isMobile(HttpServletRequest request) {
        return hasUserAgent(request, ANDROID, IPHONE, IPAD, IPOD);
    }

    public static boolean isWeiXin(HttpServletRequest request) {
        return isMobile(request) && hasUserAgent(request, WEIXIN);
    }

}
