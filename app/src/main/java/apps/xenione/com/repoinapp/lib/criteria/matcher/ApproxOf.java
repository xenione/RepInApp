package apps.xenione.com.repoinapp.lib.criteria.matcher;

import apps.xenione.com.repoinapp.lib.criteria.Matchable;

public class ApproxOf<T extends Number> implements Matchable<T> {

    public static <T extends Number> Matchable<T> approxOf(T number) {
        return new ApproxOf<>(number);
    }

    private final double deltaError = 0.00001;

    private Matchable<Double> interval;

    ApproxOf(T number) {
        interval = new Interval<>(number.doubleValue() - deltaError, number.doubleValue() + deltaError);
    }

    @Override
    public boolean match(T value) {
        return interval.match(value.doubleValue());
    }
}
