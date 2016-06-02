package com.nonvoid.andromeda.data;

import java.io.Serializable;

/**
 * Created by Matt on 6/1/2016.
 *
 * A Location is an area of the map
 * For now, a circle
 *      Center at the latlon
 *      Radius defualt = 10m
 *
 */
public class Location extends LatLon implements Serializable {

    private double radius = 10.0; //in meters

    public Location(double latitude, double longitude) {
        super(latitude, longitude);
    }
    public Location(double latitude, double longitude, double radius){
        super(latitude, longitude);
        this.radius = radius;
    }
}
