package by.epam.webtask.util;

import org.apache.commons.text.StringEscapeUtils;

public final class TextEscapeUtil {
    private TextEscapeUtil() {
    }

    public static String escapeHtml(String text) {
        return StringEscapeUtils.escapeHtml4(text);
    }
}
