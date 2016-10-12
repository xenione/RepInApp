package com.xenione.repoinapp.cuore;

/**
 * Created by Eugeni on 12/10/2016.
 */
public class Location {

    public String mName;

    public String mState;

    public String mCountry;


    Location(String name, String state, String country) {
        this.mName = name;
        this.mState = state;
        this.mCountry = country;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof Location) {
            Location other = (Location) o;
            if (other.hashCode() == this.hashCode()) {
                if (this.mName.equals(other.mName)
                        && this.mCountry.equals(other.mCountry)
                        && this.mState.equals(other.mState)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashcode = mName.hashCode();
        hashcode = 31 * hashcode + mState.hashCode();
        hashcode = 31 * hashcode + mCountry.hashCode();
        return hashcode;
    }
}
