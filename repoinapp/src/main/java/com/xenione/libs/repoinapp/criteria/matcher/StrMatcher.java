package com.xenione.libs.repoinapp.criteria.matcher;

import com.xenione.libs.repoinapp.criteria.Matchable;

import java.util.regex.Pattern;



/**
 * Created by Eugeni on 22/03/2016.
 */
public class StrMatcher implements Matchable<String> {

    private Pattern pattern;

    StrMatcher(String regExp) {
        pattern = Pattern.compile(buildRegExp(regExp));
    }

    protected String buildRegExp(String regExp) {
        return regExp;
    }

    public boolean match(String value) {
        return pattern.matcher(value).matches();
    }
}
