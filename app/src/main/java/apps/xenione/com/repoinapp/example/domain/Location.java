package apps.xenione.com.repoinapp.example.domain;

/**
 * Created by Eugeni on 03/04/2016.
 */
public class Location {
    public String placeName;
    public String district;

    Location(String placeName, String district) {
        this.placeName = placeName;
        this.district = district;
    }

    @Override
    public int hashCode() {
        return (placeName + district).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Location) {
            Location otherLocation = (Location) o;
            if (otherLocation.hashCode() == this.hashCode()) {
                if (otherLocation.placeName.equals(this.placeName) && otherLocation.district.equals(this.district)) {
                    return true;
                }
            }
        }
        return false;
    }
}
