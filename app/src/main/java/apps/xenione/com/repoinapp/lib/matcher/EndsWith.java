package apps.xenione.com.repoinapp.lib.matcher;


import apps.xenione.com.repoinapp.lib.Matchable;

public class EndsWith extends StrMatcher {

    private final static String ENDS_WITH_REG_EXP = "$";

    public static Matchable<String> endsWith(String regExp) {
        return new EndsWith(regExp);
    }

    private EndsWith(String regExp) {
        super(regExp);
    }

    protected String buildRegExp(String regExp) {
        if (regExp.endsWith(ENDS_WITH_REG_EXP)) {
            throw new IllegalArgumentException("Expression (" + regExp + ")+ already ends with :" + ENDS_WITH_REG_EXP);
        }
        return ".*" + regExp + ENDS_WITH_REG_EXP;
    }
}
