package org.txazo.wyot.util;

public abstract class PageUtil {

    public static int wrapPageNum(Integer pageNum) {
        return (pageNum != null && pageNum > 0) ? pageNum : 1;
    }

    public static int wrapPageSize(Integer pageSize, int defaultPageSize, int maxPageSize) {
        return (pageSize != null && pageSize > 0 && pageSize <= maxPageSize) ? pageSize : defaultPageSize;
    }

}
