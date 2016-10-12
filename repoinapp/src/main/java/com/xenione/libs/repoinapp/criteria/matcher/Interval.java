package com.xenione.libs.repoinapp.criteria.matcher;


import com.xenione.libs.repoinapp.criteria.Matchable;

public class Interval<T extends Number> implements Matchable<T> {

    public static <T extends Number> Matchable<T> interval(T from, T to) {
        return new Interval<>(from, to);
    }

    private T from;
    private T to;

    Interval(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public void from(T t) {
        this.from = t;
    }

    public void to(T t) {
        this.to = t;
    }

    @Override
    public boolean match(T value) {
        return (from.doubleValue() <= value.doubleValue())
                && (value.doubleValue() <= to.doubleValue());
    }
}
