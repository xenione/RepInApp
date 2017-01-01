package com.xenione.repoinapp.cuore;

import com.xenione.libs.repoinapp.DomainObject;

/**
 * Created by Eugeni on 25/09/2016.
 */
public class Note extends DomainObject {

    private String mTitle;

    private String mDescription;

    long mCreateDate;

    private Location mLocation;

    public Note(int id, String title, String description) {
        _id = id;
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
