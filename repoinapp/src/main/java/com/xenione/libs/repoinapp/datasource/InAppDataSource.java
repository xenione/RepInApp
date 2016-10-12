package com.xenione.libs.repoinapp.datasource;

import android.content.Context;

import com.xenione.libs.repoinapp.criteria.Matchable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Eugeni on 28/02/2016.
 */
public class InAppDataSource<T> implements DataSource<T> {

    private SharePreferenceHelper helper;
    private Type type;

    public InAppDataSource(Context context, Type type) {
        this.type = type;
        helper = new SharePreferenceHelper(context, "inAppDB");
    }

    @Override
    public List<DataObject<T>> select(Matchable<T> criteria) {
        Map<String, String> map = helper.getAll();
        List<DataObject<T>> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            DataObject<T> dataObject = DataObject.from(type, entry.getValue());
            if (criteria.match(dataObject.target)) {
                list.add(dataObject);
            }
        }
        return list;
    }

    @Override
    public boolean update(DataObject<T> t) {
        helper.put(t._id, t.serialize());
        return true;
    }

    @Override
    public long save(DataObject<T> t) {
        helper.put(t._id, t.serialize());
        return t._id;
    }

    @Override
    public void delete(DataObject<T> t) {
        helper.remove(t._id);
    }

    @Override
    public void clear() {
        helper.clear();
    }
}