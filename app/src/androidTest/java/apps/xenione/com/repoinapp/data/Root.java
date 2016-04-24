package apps.xenione.com.repoinapp.data;


import apps.xenione.com.repoinapp.lib.datasource.DataObject;

/**
 * Created by Eugeni on 25/03/2016.
 */
public class Root extends DataObject {

    private long id;

    private String propertyOne;

    private String propertyTwo;

    private Dependent dependent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        _id = id;
        this.id = id;
    }

    public String getPropertyOne() {
        return propertyOne;
    }

    public void setPropertyOne(String propertyOne) {
        this.propertyOne = propertyOne;
    }

    public String getPropertyTwo() {
        return propertyTwo;
    }

    public void setPropertyTwo(String propertyTwo) {
        this.propertyTwo = propertyTwo;
    }

    public Dependent getDependent() {
        return dependent;
    }

    public void setDependent(Dependent dependent) {
        this.dependent = dependent;
    }
}
