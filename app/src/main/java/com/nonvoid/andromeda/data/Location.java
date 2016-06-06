package com.nonvoid.andromeda.data;

import com.google.android.gms.maps.model.LatLng;

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
public class Location implements Serializable {

    public LatLng center;
    private double radius = 0;

    //Constructors
    public Location(LatLng center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    public Location(LatLng center) {
        this.center = center;
    }

    //Getters and Setters
    public LatLng getCenter() {
        return center;
    }
    public void setCenter(LatLng center) {
        this.center = center;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Location{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Double.compare(location.radius, radius) != 0) return false;
        return center.equals(location.center);

    }
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = center.hashCode();
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double distanceTo(Location loc){
        //A^2 + B^2 = C^2
        //Hypotenuse
        return distanceTo(loc.center);
        /*
        return Math.hypot(
                (this.center.latitude-loc.center.latitude),  //length of X
                (this.center.longitude-loc.center.longitude) //length of y
        );*/
    }
    public double distanceTo(LatLng loc){
        return Math.hypot(
                (this.center.latitude-loc.latitude),  //length of X
                (this.center.longitude-loc.longitude) //length of y
        );
    }

    public boolean checkDistance(LatLng loc){
        if(this.distanceTo(loc) < this.radius)
            return true;
        return false;
    }
}
