package com.xenione.libs.repoinapp.criteria.matcher;


import com.xenione.libs.repoinapp.criteria.Matchable;

/**
 * Created by Eugeni on 05/07/2016.
 */
public class Any<T> implements Matchable<T> {

    public static <T> Matchable<T> any() {
        return new Any<>();
    }

    @Override
    public boolean match(T aObject) {
        return true;
    }
}
