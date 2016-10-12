package com.xenione.repoinapp.cuore;

/**
 * Created by Eugeni on 25/09/2016.
 */
public class Note {

    private String mTitle;

    private String mDescription;

    long mCreateDate;

    private Location mLocation;

    public Note(String title, String description) {
        mTitle = title;
        mDescription = description;
        mCreateDate = System.currentTimeMillis();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setLocation(String cityName, String state, String country) {
        mLocation = new Location(cityName, state, country);
    }
}
