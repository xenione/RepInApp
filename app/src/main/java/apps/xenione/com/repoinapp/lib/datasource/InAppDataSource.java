package apps.xenione.com.repoinapp.lib.datasource;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import apps.xenione.com.repoinapp.lib.DataSource;
import apps.xenione.com.repoinapp.lib.criteria.Matchable;

/**
 * Created by Eugeni on 28/02/2016.
 */
public class InAppDataSource<T extends DataObject> implements DataSource<T> {

    private SharePreferenceHelper helper;
    private Class<T> type;

    public InAppDataSource(Context context, Class<T> type) {
        helper = new SharePreferenceHelper(context, "inAppDB");
        this.type = type;
    }

    @Override
    public List<T> selector(Matchable<T> criteria) {
        Map<String, String> map = helper.getAll();
        List<T> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            T register = DataObject.from(type, entry.getValue());
            if (criteria.match(register)) {
                list.add(register);
            }
        }
        return list;
    }

    @Override
    public boolean update(T t) {
        helper.put(t._id, t.serialize());
        return true;
    }

    @Override
    public long save(T t) {
        helper.put(t._id, t.serialize());
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
}