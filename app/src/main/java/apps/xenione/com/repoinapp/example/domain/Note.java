package apps.xenione.com.repoinapp.example.domain;

import java.io.Serializable;

import apps.xenione.com.repoinapp.lib.datasource.DataObject;

public class Note extends DataObject implements Serializable {

    private long id;

    private String title;

    private String description;

    private Location location;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        _id = id;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String placeName, String district) {
        location = new Location(placeName, district);
    }
}
