package apps.xenione.com.repoinapp.lib.criteria.matcher;

import apps.xenione.com.repoinapp.lib.criteria.Matchable;

public class AllOf<T> implements Matchable<T> {

    public static <T> Matchable<T> allOf(Matchable<T>... matchables) {
        return new AllOf<>(matchables);
    }

    private Matchable<T>[] matchables;

    @SuppressWarnings("varargs")
    AllOf(Matchable<T>... matchables) {
        this.matchables = matchables;
    }

    @Override
    public boolean match(T aObject) {
        for (Matchable<T> matchable : matchables) {
            if (!matchable.match(aObject)) {
                return false;
            }
        }
        return true;
    }
}