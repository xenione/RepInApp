package apps.xenione.com.repoinapp.lib;


import java.util.List;

import apps.xenione.com.repoinapp.lib.criteria.Matchable;

public interface DataSource<T> {

    List<T> selector(Matchable<T> criteria);

    boolean update(T t);

    long save(T t);

    void delete(T t);

    void clear();
}
