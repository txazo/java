package org.txazo.tool.util;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class DecimalFormatUtils {

    private static ThreadLocal<DecimalFormatWrapper> decimalFormatWrapper = new ThreadLocal<DecimalFormatWrapper>() {

        @Override
        protected DecimalFormatWrapper initialValue() {
            return new DecimalFormatWrapper();
        }

    };

    public static String format(String pattern, double number) {
        return decimalFormatWrapper.get().format(pattern, number);
    }

    private static class DecimalFormatWrapper {

        private ConcurrentMap<String, DecimalFormat> decimalFormatMap = new ConcurrentHashMap<>();

        public String format(String pattern, double number) {
            return getDecimalFormat(pattern).format(number);
        }

        private DecimalFormat getDecimalFormat(String pattern) {
            DecimalFormat decimalFormat = decimalFormatMap.get(pattern);
            if (decimalFormat == null) {
                decimalFormat = new DecimalFormat(pattern);
                DecimalFormat previous = decimalFormatMap.putIfAbsent(pattern, decimalFormat);
                if (previous != null) {
                    decimalFormat = previous;
                }
            }
            return decimalFormat;
        }

    }

}
