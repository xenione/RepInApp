package apps.xenione.com.repoinapp.lib.matcher;

import java.util.regex.Pattern;

import apps.xenione.com.repoinapp.lib.Matchable;


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
