package apps.xenione.com.repoinapp.lib.matcher;


import apps.xenione.com.repoinapp.lib.Matchable;

/**
 * Created by Eugeni on 23/03/2016.
 */
public class StartsWith extends StrMatcher {

    private final static String START_WITH_REG_EXP = "^";

    public static Matchable<String> startWith(String regExp) {
        return new StartsWith(regExp);
    }

    private StartsWith(String regExp) {
        super(regExp);
    }

    protected String buildRegExp(String regExp) {
        if (regExp.startsWith(START_WITH_REG_EXP)) {
            throw new IllegalArgumentException("Expression (" + regExp + ")+ already starts with :" + START_WITH_REG_EXP);
        }
        return START_WITH_REG_EXP + regExp + ".*";
    }
}
