package com.xenione.libs.repoinapp.datasource;


import com.xenione.libs.repoinapp.criteria.Matchable;

import java.util.List;


public interface DataSource<T> {

    List<DataObject<T>> select(Matchable<T> criteria);

    boolean update(DataObject<T> t);

    long save(DataObject<T> t);

    void delete(DataObject<T> t);

    void clear();
}
