package apps.xenione.com.repoinapp.lib.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import java.util.Map;

/**
 * Created by Eugeni on 23/03/2016.
 */
public class SharePreferenceHelper {

    private final SharedPreferences sharedPref;

    public SharePreferenceHelper(Context context, String name) {
        this.sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getAll() {
        return (Map<String, String>) sharedPref.getAll();
    }

    public String get(long id) {
        return sharedPref.getString(String.valueOf(id), null);
    }

    public String get(String key) {
        return sharedPref.getString(key, null);
    }

    public void remove(long id) {
        remove(String.valueOf(id));
    }

    public void remove(String key) {
        sharedPref.edit().remove(key).apply();
    }

    public void put(long id, String value) {
        put(String.valueOf(id), value);
    }

    public void put(String key, String value) {
        sharedPref.edit().putString(key, value).apply();
    }

    public void clear() {
        sharedPref.edit().clear().apply();
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sharedPref.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
