package apps.xenione.com.repoinapp.lib.datasource;

import com.google.gson.Gson;

/**
 * Created by Eugeni on 22/03/2016.
 */
public class DataObject implements Serializable {

    public long _id;

    public static <T extends DataObject> T from(Class<T> type, String serialize) {
        return new Gson().fromJson(serialize, type);
    }

    @Override
    public String serialize() {
        return new Gson().toJson(this);
    }
}
