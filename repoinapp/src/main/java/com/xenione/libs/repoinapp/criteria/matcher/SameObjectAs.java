package com.xenione.libs.repoinapp.criteria.matcher;


import com.xenione.libs.repoinapp.criteria.Matchable;

/**
 * Created by Eugeni on 03/04/2016.
 */
public class SameObjectAs<T> implements Matchable<T> {

    public static Matchable<Long> sameAs(Long value) {
        return new SameObjectAs<>(value);
    }

    public static Matchable<Integer> sameAs(Integer value) {
        return new SameObjectAs<>(value);
    }

    public static Matchable<Float> sameAs(Float value) {
        return new SameObjectAs<>(value);
    }

    public static Matchable<Double> sameAs(Double value) {
        return new SameObjectAs<>(value);
    }

    public static Matchable<String> sameAs(String value) {
        return new SameObjectAs<>(value);
    }

    public static <T> Matchable<T> sameObjectAs(T object) {
        return new SameObjectAs<>(object);
    }

    T thisObject;

    private SameObjectAs(T object) {
        thisObject = object;
    }

    @Override
    public boolean match(T aObject) {
        return thisObject.equals(aObject);
    }
}
