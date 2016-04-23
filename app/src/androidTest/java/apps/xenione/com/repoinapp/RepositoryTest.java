package apps.xenione.com.repoinapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import apps.xenione.com.repoinapp.lib.BaseRepository;
import apps.xenione.com.repoinapp.lib.DataSource;
import apps.xenione.com.repoinapp.lib.Matchable;

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

    private BaseRepository baseRepository;
    private DataSource dataSource;
    private Matchable matchable;
    private Object object = new Object();

    @Before
    public void setup() {
        dataSource = mock(DataSource.class);
        baseRepository = new BaseRepository(dataSource);
        matchable = mock(Matchable.class);
    }

    @Test
    public void matchTest() {
        baseRepository.match(matchable);
        verify(dataSource).selector(same(matchable));
    }

    @Test
    public void matchSingleTest() {
        Object dataRecordOne = new Object();
        Object dataRecordTwo = new Object();
        List list = new ArrayList();
        list.add(dataRecordOne);
        list.add(dataRecordTwo);
        when(dataSource.selector(any(Matchable.class))).thenReturn(list);
        Object retrieved = baseRepository.matchSingle(matchable);
        assertEquals(dataRecordOne, retrieved);
    }

    @Test
    public void updateTest() {
        baseRepository.update(object);
        verify(dataSource).update(same(object));
    }

    @Test
    public void saveTest() {
        baseRepository.save(object);
        verify(dataSource).save(same(object));
    }

    @Test
    public void deleteTest() {
        baseRepository.delete(object);
        verify(dataSource).delete(same(object));
    }

    @Test
    public void deleteAll() {
        baseRepository.delete();
        verify(dataSource).clear();
    }
}
