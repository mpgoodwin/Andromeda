package com.nonvoid.andromeda.data;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Matt on 6/21/2016.
 */
public class Hint {
    String description;
    LatLng location;

    public Hint() {
    }

    public Hint(String description, LatLng location) {
        this.description = description;
        this.location = location;
    }

    public Hint(String description) {
        this.description = description;
    }

    public Hint(LatLng location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
