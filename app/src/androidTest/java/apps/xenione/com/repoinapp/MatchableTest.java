package apps.xenione.com.repoinapp;

import org.junit.Before;
import org.junit.Test;

import apps.xenione.com.repoinapp.lib.Matchable;

import static junit.framework.Assert.assertTrue;
import static apps.xenione.com.repoinapp.lib.matcher.StartsWith.startWith;
import static apps.xenione.com.repoinapp.lib.matcher.EndsWith.endsWith;
import static apps.xenione.com.repoinapp.lib.matcher.SameObjectAs.sameAs;
import static apps.xenione.com.repoinapp.lib.matcher.Interval.interval;
import static apps.xenione.com.repoinapp.lib.matcher.ApproxOf.approxOf;
import static apps.xenione.com.repoinapp.lib.matcher.AllOf.allOf;
import static apps.xenione.com.repoinapp.lib.matcher.AnyOf.anyOf;

/**
 * Created by Eugeni on 24/03/2016.
 */
public class MatchableTest {

    @Before
    public void setup() {
    }

    @Test
    public void startWithTest() {
        Matchable<String> startWith = startWith("prop");
        assertTrue(startWith.match("propertyOne"));
    }

    @Test
    public void endWithTest() {
        Matchable<String> endWith = endsWith("ne");
        assertTrue(endWith.match("propertyOne"));
    }

    @Test
    public void sameAsStringsTest() {
        Matchable<String> sameAs = sameAs("propertyOne");
        assertTrue(sameAs.match("propertyOne"));
    }

    @Test
    public void sameAsIntegerTest() {
        Matchable<Integer> sameAs = sameAs(1);
        assertTrue(sameAs.match(1));
    }

    @Test
    public void sameAsDoubleTest() {
        Matchable<Double> sameAs = sameAs(1.0d);
        assertTrue(sameAs.match(1.0d));
    }

    @Test
    public void sameAsFloatTest() {
        Matchable<Float> sameAs = sameAs(1.0f);
        assertTrue(sameAs.match(1.0f));
    }

    @Test
    public void intervalTest() {
        Matchable<Integer> interval = interval(0, 10);
        assertTrue(interval.match(5));
    }

    @Test
    public void approxOfTest() {
        Matchable<Double> approxOf = approxOf(4.34355);
        assertTrue(approxOf.match(4.34355));
    }

    @Test
    public void allOfTest() {
        Matchable<String> sameAs = sameAs("propertyOne");
        Matchable<String> startWith = startWith("prop");
        Matchable<String> allOf = allOf(sameAs, startWith);
        assertTrue(allOf.match("propertyOne"));
    }

    @Test
    public void anyOfTest() {
        Matchable<String> sameAs = sameAs("propertyOne");
        Matchable<String> startWith = startWith("field");
        Matchable<String> anyOf = anyOf(sameAs, startWith);
        assertTrue(anyOf.match("propertyOne"));
    }
}
