package com.xenione.libs.repoinapp.criteria.matcher;


import com.xenione.libs.repoinapp.criteria.Matchable;

/**
 * Created by Eugeni on 23/03/2016.
 */
public class Contains extends StrMatcher {

    private final static String REG_EXP_CONTAINS = ".*";

    public static Matchable<String> contains(String regExp) {
        return new Contains(regExp);
    }

    private Contains(String regExp) {
        super(regExp);
    }

    protected String buildRegExp(String regExp) {
        String prepend = "";
        if (!regExp.startsWith(REG_EXP_CONTAINS)) {
            prepend = REG_EXP_CONTAINS;
        }
        StringBuilder builder = new StringBuilder(prepend);
        builder.append(regExp);
        builder.append(REG_EXP_CONTAINS);
        return builder.toString();
    }
}
