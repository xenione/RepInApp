package com.xenione.libs.repoinapp.datasource;

import android.content.Context;

import com.google.gson.Gson;
import com.xenione.libs.repoinapp.DomainObject;
import com.xenione.libs.repoinapp.criteria.Matchable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Eugeni on 28/02/2016.
 */
public class InAppDataSource<T extends DomainObject> implements DataSource<T> {

    private SharePreferenceHelper helper;
    private Serializer<T> serializer;

    public InAppDataSource(Context context, Serializer<T> serializer) {
        helper = new SharePreferenceHelper(context, "inAppDB");
        this.serializer = serializer;
    }

    @Override
    public List<T> select(Matchable<T> criteria) {
        Map<String, String> map = helper.getAll();
        List<T> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            T object = serializer.deserialize(entry.getValue());
            if (criteria.match(object)) {
                list.add(object);
            }
        }
        return list;
    }

    @Override
    public boolean update(T t) {
        helper.put(t._id, serializer.serialize(t));
        return true;
    }

    @Override
    public long save(T t) {
        helper.put(t._id, serializer.serialize(t));
        return t._id;
    }

    @Override
    public void delete(T t) {
        helper.remove(t._id);
    }

    @Override
    public void clear() {
        helper.clear();
    }

    public static class Serializer<T> implements Serializable<T> {

        private Gson gson = new Gson();
        private Class<T> type;

        public Serializer(Class<T> type) {
            this.type = type;
        }

        public T deserialize(String serialized) {
            return gson.fromJson(serialized, type);
        }

        public String serialize(T object) {
            return gson.toJson(object);
        }
    }
}