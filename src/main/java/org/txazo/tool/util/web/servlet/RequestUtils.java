package org.txazo.tool.util.web.servlet;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * RequestUtils
 */
public abstract class RequestUtils {

    public static String getRequestURL(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String query = request.getQueryString();
        return url + (StringUtils.isBlank(query) ? StringUtils.EMPTY : "?" + query);
    }

    public static String getRequestURL(HttpServletRequest request, String... excludeParams) {
        String url = request.getRequestURL().toString();
        Set<String> excludeParamSet = new HashSet<String>();
        if (ArrayUtils.isNotEmpty(excludeParams)) {
            Collections.addAll(excludeParamSet, excludeParams);
        }
        int index = 0;
        String paramName = null;
        StringBuilder paramsString = new StringBuilder();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            if (!excludeParamSet.contains(paramName)) {
                paramsString.append(index++ == 0 ? "?" : "&").append(paramName).append("=").append(request.getParameter(paramName));
            }
        }
        return url + paramsString.toString();
    }

}
