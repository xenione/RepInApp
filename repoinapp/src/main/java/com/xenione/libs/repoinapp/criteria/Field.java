package com.xenione.libs.repoinapp.criteria;

import android.util.Property;

public class Field<T, V> {

    private Property<T, V> property;

    public Field(Class<T> hostType, String propertyName, Class<V> propertyValueType) {
        property = Property.of(hostType, propertyValueType, propertyName);
    }

    @SuppressWarnings("unchecked")
    public V getValue(T aObject) throws IllegalAccessException, NoSuchFieldException {
        java.lang.reflect.Field field = aObject.getClass().getDeclaredField(property.getName());
        field.setAccessible(true);
        return property.getType().cast(field.get(aObject));
    }
}
