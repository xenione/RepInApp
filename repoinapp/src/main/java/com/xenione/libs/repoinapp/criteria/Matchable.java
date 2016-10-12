package com.xenione.libs.repoinapp.criteria;

/**
 * Created by Eugeni on 24/03/2016.
 */
public interface Matchable<T> {

    boolean match(T aObject);
}
