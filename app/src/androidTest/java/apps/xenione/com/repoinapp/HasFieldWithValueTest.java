package apps.xenione.com.repoinapp;

import org.junit.Before;
import org.junit.Test;

import apps.xenione.com.repoinapp.data.Dependent;
import apps.xenione.com.repoinapp.data.Root;
import apps.xenione.com.repoinapp.lib.criteria.HasFieldWithValue;

import static apps.xenione.com.repoinapp.lib.criteria.matcher.SameObjectAs.sameAs;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Eugeni on 27/03/2016.
 */
public class HasFieldWithValueTest {
    private static final String DEPENDENT_PROPERTY_ONE = "dep_propOne";
    private static final String DEPENDENT_PROPERTY_TWO = "dep_propTwo";
    private static final String ROOT_PROPERTY_ONE = "root_propOne";
    private static final String ROOT_PROPERTY_TWO = "root_propTwo";

    private Dependent dependent;
    private Root root;

    @Before
    public void setup() {
        dependent = new Dependent();
        dependent.setPropertyOne(DEPENDENT_PROPERTY_ONE);
        dependent.setPropertyTwo(DEPENDENT_PROPERTY_TWO);
        root = new Root();
        root.setPropertyOne(ROOT_PROPERTY_ONE);
        root.setPropertyTwo(ROOT_PROPERTY_ONE);
        root.setDependent(dependent);
    }

    @Test
    public void hasValueWith() {
        HasFieldWithValue<Dependent, String> hasFieldWithValue = new HasFieldWithValue.Builder<Dependent, String>()
                .of(Dependent.class)
                .withProperty("propertyOne", String.class)
                .withValue(sameAs(DEPENDENT_PROPERTY_ONE)).build();

        assertTrue(hasFieldWithValue.match(dependent));
    }

    @Test
    public void hasValueWithInDependentObject() {
        HasFieldWithValue<Dependent, String> hasDependentFieldWithValue = new HasFieldWithValue.Builder<Dependent, String>()
                .of(Dependent.class)
                .withProperty("propertyOne", String.class)
                .withValue(sameAs(DEPENDENT_PROPERTY_ONE)).build();

        HasFieldWithValue<Root, Dependent> hasFieldWithValue = new HasFieldWithValue.Builder<Root, Dependent>()
                .of(Root.class)
                .withProperty("dependent", Dependent.class)
                .withValue(hasDependentFieldWithValue).build();

        assertTrue(hasFieldWithValue.match(root));
    }
}
