package org.txazo.tool.util.lang;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * CharacterUtils
 */
public abstract class CharacterUtils {

    private static Set<Character.UnicodeBlock> chineseUBSet = new HashSet<>();

    private static final Character.UnicodeBlock[] chineseUBs = new Character.UnicodeBlock[]{
            Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS,
            Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS,
            Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A,
            Character.UnicodeBlock.GENERAL_PUNCTUATION,
            Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION,
            Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
    };

    static {
        Collections.addAll(chineseUBSet, chineseUBs);
    }

    /**
     * 是否中文
     */
    public static boolean isChinese(char c) {
        return chineseUBSet.contains(Character.UnicodeBlock.of(c));
    }

}
