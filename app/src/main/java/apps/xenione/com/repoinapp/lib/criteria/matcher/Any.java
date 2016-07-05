package apps.xenione.com.repoinapp.lib.criteria.matcher;

import apps.xenione.com.repoinapp.lib.criteria.Matchable;

/**
 * Created by Eugeni on 05/07/2016.
 */
public class Any implements Matchable<Object> {

    public static Matchable<Object> any() {
        return new Any();
    }

    @Override
    public boolean match(Object aObject) {
        return true;
    }
}
