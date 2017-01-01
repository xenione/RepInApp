package com.xenione.libs.repoinapp.datasource;

/**
 * Created by Eugeni on 29/12/2016.
 */
public interface Serializable<T> {

    String serialize(T t);

    T deserialize(String des);
}
