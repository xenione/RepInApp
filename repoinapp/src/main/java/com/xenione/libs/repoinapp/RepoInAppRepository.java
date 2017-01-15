package com.xenione.libs.repoinapp;

import android.util.SparseArray;

import com.xenione.libs.repoinapp.criteria.Matchable;
import com.xenione.libs.repoinapp.datasource.DataSource;

import java.util.ArrayList;
import java.util.List;


public class RepoInAppRepository<T extends DomainObject>  {

    private DataSource<T> mDataSource;

    private SparseArray<T> registry = new SparseArray<>();

    public RepoInAppRepository(DataSource<T> dataSource) {
        this.mDataSource = dataSource;
    }

    public List<T> match(Matchable<T> criteria) {
        List<T> selects = mDataSource.select(criteria);
        List<T> matches = new ArrayList<>(selects.size());
        for (T select : selects) {
            T added = registry.get(select._id);
            if (added == null) {
                registry.put(select._id, select);
                added = select;
            }
            matches.add(added);
        }
        return matches;
    }

    public T matchSingle(Matchable<T> someCriteria) {
        List<T> matches = match(someCriteria);
        if (matches.isEmpty()) {
            return null;
        }
        return matches.get(0);
    }

    public boolean update(T t) {
        return mDataSource.update(t);
    }

    public long save(T t) {
        registry.put(t._id, t);
        return mDataSource.save(t);
    }

    public void delete(T t) {
        registry.remove(t._id);
        mDataSource.delete(t);
    }

    public void delete() {
        registry.clear();
        mDataSource.clear();
    }
}
