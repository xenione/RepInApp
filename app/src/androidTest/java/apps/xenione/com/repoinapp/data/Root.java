package apps.xenione.com.repoinapp.data;


/**
 * Created by Eugeni on 25/03/2016.
 */
public class Root {

    private String propertyOne;

    private String propertyTwo;

    private Dependent dependent;

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
