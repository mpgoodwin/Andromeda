package com.nonvoid.andromeda.data;

import com.google.android.gms.maps.model.LatLng;
import com.nonvoid.andromeda.data.Location;

/**
 * Created by Quinten on 6/6/2016.
 */
public class Hint extends Location {
    String text;

    public Hint(LatLng center) {
        super(center);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
