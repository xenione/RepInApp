package apps.xenione.com.repoinapp;

import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import apps.xenione.com.repoinapp.data.Root;
import apps.xenione.com.repoinapp.lib.criteria.Matchable;
import apps.xenione.com.repoinapp.lib.datasource.DataObject;
import apps.xenione.com.repoinapp.lib.datasource.InAppDataSource;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class InAppDataSourceTest {

    private InAppDataSource inAppDataSource;
    private Matchable matchable;

    @Before
    public void setup() {
        inAppDataSource = new InAppDataSource(InstrumentationRegistry.getContext(), Root.class);
        DataObject dataObject1 = DataObject.from(new Root());
        DataObject dataObject2 = DataObject.from(new Root());
        inAppDataSource.save(dataObject1);
        inAppDataSource.save(dataObject2);
        matchable = mock(Matchable.class);
    }

    @Test
    public void selectorTest() {
        inAppDataSource.select(matchable);
        verify(matchable, times(2)).match(any(DataObject.class));
    }

}
