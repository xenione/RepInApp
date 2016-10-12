package com.xenione.libs.repoinapp;

import com.xenione.libs.repoinapp.criteria.Matchable;
import com.xenione.libs.repoinapp.datasource.DataObject;
import com.xenione.libs.repoinapp.datasource.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class BaseRepository<T> {

    private DataSource<T> mDataSource;

    private Map<DataObject<T>, T> map = new HashMap<>();

    public BaseRepository(DataSource<T> dataSource) {
        this.mDataSource = dataSource;
    }

    public List<T> match(Matchable<T> criteria) {
        List<DataObject<T>> selects = mDataSource.select(criteria);
        List<T> matches = new ArrayList<>(selects.size());
        for (DataObject<T> select : selects) {
            T added = map.get(select);
            if (added == null) {
                added = select.target;
                map.put(select, added);
            }
            matches.add(added);
        }
        return matches;
    }

    public T matchSingle(Matchable<T> someCriteria) {
        List<T> matches = match(someCriteria);
        if (matches.isEmpty()) {
            return fallback(someCriteria);
        }
        return matches.get(0);
    }

    public T fallback(Matchable<T> someCriteria){
        return null;
    }

    public boolean update(T t) {
        return mDataSource.update(getDataObject(t));
    }

    public long save(T t) {
        DataObject<T> dataObject = DataObject.from(t);
        map.put(dataObject, t);
        return mDataSource.save(dataObject);
    }

    public void delete(T t) {
        DataObject<T> dataObject = getDataObject(t);
        map.remove(dataObject);
        mDataSource.delete(dataObject);
    }

    public void delete() {
        map.clear();
        mDataSource.clear();
    }

    private DataObject<T> getDataObject(T t) {
        for (Map.Entry<DataObject<T>, T> entry : map.entrySet()) {
            if (entry.getValue() == t) {
                return entry.getKey();
            }
        }
        return null;
    }
}
