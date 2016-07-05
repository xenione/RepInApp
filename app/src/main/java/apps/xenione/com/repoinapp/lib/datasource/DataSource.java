package apps.xenione.com.repoinapp.lib.datasource;


import java.util.List;

import apps.xenione.com.repoinapp.lib.criteria.Matchable;

public interface DataSource<T> {

    List<DataObject<T>> select(Matchable<T> criteria);

    boolean update(DataObject<T> t);

    long save(DataObject<T> t);

    void delete(DataObject<T> t);

    void clear();
}
