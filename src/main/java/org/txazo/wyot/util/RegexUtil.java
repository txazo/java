package org.txazo.wyot.util;

import java.util.regex.Pattern;

public abstract class RegexUtil {

    // 邮箱
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9][.\\w]*@(\\w)+((\\.\\w+)+)$");

    // 图片(不带后缀参数)
    private static final Pattern PATTERN_IMAGE = Pattern.compile("^http(s)?://[\\w\\-]+(\\.[\\w\\-]+)+.*$");

    // 手机号
    private static final Pattern PATTERN_MOBILE = Pattern.compile("^1(([3,4,5,7,8][0-9])|(66)|(9[8,9]))[0-9]{8}$");

    // QQ号
    private static final Pattern PATTERN_QQ = Pattern.compile("^[1-9][0-9]{4,10}$");

    // 中文名
    private static final Pattern PATTERN_CHINESE_NAME = Pattern.compile("^[\\u4e00-\\u9fa5]{2,6}$");

    // 身份证号
    private static final Pattern PATTERN_ID_CARD = Pattern.compile("^[1-9]\\d{16}[0-9X]$");

    public static boolean isValidEmail(String email) {
        return PATTERN_EMAIL.matcher(email).matches();
    }

    public static boolean isValidImage(String image) {
        return PATTERN_IMAGE.matcher(image).matches();
    }

    public static boolean isValidMobile(String mobile) {
        return PATTERN_MOBILE.matcher(mobile).matches();
    }

    public static boolean isValidQQ(String qq) {
        return PATTERN_QQ.matcher(qq).matches();
    }

    public static boolean isValidChineseName(String name) {
        return PATTERN_CHINESE_NAME.matcher(name).matches();
    }

    public static boolean isValidIdCard(String idcard) {
        return PATTERN_ID_CARD.matcher(idcard).matches();
    }

}
