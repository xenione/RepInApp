package com.xenione.libs.repoinapp.datasource;


import com.xenione.libs.repoinapp.criteria.Matchable;

import java.util.List;


public interface DataSource<T> {

    List<T> select(Matchable<T> criteria);

    boolean update(T t);

    long save(T t);

    void delete(T t);

    void clear();
}
