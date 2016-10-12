package com.xenione.libs.repoinapp.criteria;

public class HasFieldWithValue<T, V> implements Matchable<T> {

    private Field<T, V> field;
    private Matchable<V> comparable;

    private HasFieldWithValue(Builder<T, V> builder) {
        this.field = builder.field;
        this.comparable = builder.matchable;
    }

    @Override
    public boolean match(T aObject) {
        try {
            return comparable.match(field.getValue(aObject));
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static class Builder<T, V> {

        Field<T, V> field;
        Matchable<V> matchable;
        private Class<T> hostType;
        private String propertyName;
        private Class<V> propertyType;

        public Builder<T, V> of(Class<T> clazz) {
            this.hostType = clazz;
            return this;
        }

        public Builder<T, V> withProperty(String name, Class<V> type) {
            this.propertyName = name;
            this.propertyType = type;
            return this;
        }

        public Builder<T, V> withValue(Matchable<V> comparable) {
            matchable = comparable;
            return this;
        }

        public HasFieldWithValue<T, V> build() {
            field = new Field<>(hostType, propertyName, propertyType);
            return new HasFieldWithValue<>(this);
        }
    }
}
