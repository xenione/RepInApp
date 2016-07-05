package apps.xenione.com.repoinapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import apps.xenione.com.repoinapp.lib.BaseRepository;
import apps.xenione.com.repoinapp.lib.criteria.Matchable;
import apps.xenione.com.repoinapp.lib.criteria.matcher.Any;
import apps.xenione.com.repoinapp.lib.datasource.DataObject;
import apps.xenione.com.repoinapp.lib.datasource.DataSource;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Eugeni on 23/04/2016.
 */
public class RepositoryTest {

    private BaseRepository<Object> baseRepository;
    private DataSource<Object> dataSource;
    private Matchable<Object> matchable;
    private Object object = new Object();


    @Before
    public void setup() {
        dataSource = mock(DataSource.class);
        matchable = mock(Matchable.class);
        baseRepository = new BaseRepository<>(dataSource);
    }

    @Test
    public void matchTest() {
        baseRepository.match(matchable);
        verify(dataSource).select(same(matchable));
    }

    @Test
    public void matchSingleTest() {
        List<DataObject> list = new ArrayList<>();
        Object object = new Object();
        DataObject dataRecordOne = DataObject.from(object);
        list.add(dataRecordOne);
        when(dataSource.select(any(Matchable.class))).thenReturn(list);
        Object retrieved = baseRepository.matchSingle(matchable);
        assertEquals(object, retrieved);
    }

    @Test
    public void returnSameObjectInstanceFromTwoQueries() {
        List<DataObject> list = new ArrayList<>();
        list.add(DataObject.from(new Object()));
        when(dataSource.select(any(Matchable.class))).thenReturn(list);
        Object retrieved1 = baseRepository.matchSingle(Any.any());
        Object retrieved2 = baseRepository.matchSingle(Any.any());
        assertEquals(retrieved1, retrieved2);
    }

    @Test
    public void updateObjectAndPersistTest() {
        baseRepository.update(object);
        verify(dataSource).update(any(DataObject.class));
    }

    @Test
    public void saveObjectAndPersistTest() {
        baseRepository.save(object);
        verify(dataSource).save(any(DataObject.class));
    }

    @Test
    public void saveObjectAndRetrieveSameInstanceTest() {
        List list = new ArrayList();
        list.add(DataObject.from(object));
        when(dataSource.select(any(Matchable.class))).thenReturn(list);
        baseRepository.save(object);
        Object objectRetrieved = baseRepository.matchSingle(Any.any());
        assertEquals(object, objectRetrieved);
    }

    @Test
    public void deleteObjectAndPersistTest() {
        baseRepository.delete(object);
        verify(dataSource).delete(any(DataObject.class));
    }

    @Test
    public void deleteAllTest() {
        baseRepository.delete();
        verify(dataSource).clear();
    }
}
