package apps.xenione.com.repoinapp.lib.datasource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Eugeni on 22/03/2016.
 */
public class DataObject<T> implements Serializable {

    public int _id;

    public T target;

    private DataObject(T t) {
        _id = t.hashCode();
        target = t;
    }

    public static <T> DataObject<T> from(Class<T> type, String serialize) {
        return new Gson().fromJson(serialize, new TypeToken<DataObject<T>>() {
        }.getType());
    }

    public static <T> DataObject<T> from(T t) {
        return new DataObject<>(t);
    }

    @Override
    public String serialize() {
        return new Gson().toJson(this);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof DataObject) {
            return ((DataObject) other)._id == _id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return _id;
    }

}
