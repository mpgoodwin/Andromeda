package com.nonvoid.andromeda.data;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Matt on 6/21/2016.
 */
public class Hint {
    String description;
    LatLng location;

    public Hint(String description, LatLng location) {
        this.description = description;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public LatLng getLocation() {
        return location;
    }
}
