package apps.xenione.com.repoinapp.lib.criteria.matcher;

import apps.xenione.com.repoinapp.lib.criteria.Matchable;

public class AnyOf<T> implements Matchable<T> {

    public static <T> Matchable<T> anyOf(Matchable<T>... matchables) {
        return new AnyOf<>(matchables);
    }

    Matchable<T>[] matchables;

    @SuppressWarnings("varargs")
    AnyOf(Matchable<T>... matchables) {
        this.matchables = matchables;
    }

    @Override
    public boolean match(T aObject) {
        for (Matchable<T> matchable : matchables) {
            if (matchable.match(aObject)) {
                return true;
            }
        }
        return false;
    }
}
