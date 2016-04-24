package apps.xenione.com.repoinapp;

import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import apps.xenione.com.repoinapp.lib.datasource.SharePreferenceHelper;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Eugeni on 24/04/2016.
 */
public class SharePreferenceHelperTest {

    private SharePreferenceHelper helper;
    private long id = 1;
    private String serialize = "data serializable";

    @Before
    public void setup() {
        helper = new SharePreferenceHelper(InstrumentationRegistry.getContext(), "inAppDB");
        helper.put(id, serialize);
    }

    @After
    public void teardown() {
        helper.clear();
    }

    @Test
    public void getTest() {
        String retrieved = helper.get(id);
        assertThat(retrieved, is(equalTo(serialize)));
    }

    @Test
    public void removeTest() {
        helper.remove(id);
        String retrieved = helper.get(id);
        assertNull(retrieved);
    }

    @Test
    public void putTest() {
        String serialize = " data to serialize with id = 2";
        long id = this.id;
        helper.put(id, serialize);
        String retrieved = helper.get(id);
        assertThat(retrieved, is(equalTo(serialize)));
    }

    @Test
    public void updateTest() {
        String serialize = " data to serialize with id = 2";
        long id = 2;
        helper.put(id, serialize);
        String retrieved = helper.get(id);
        assertThat(retrieved, is(equalTo(serialize)));
    }

    @Test
    public void clearTest() {
        helper.clear();
        String retrieved = helper.get(id);
        assertNull(retrieved);
    }
}
