package com.nonvoid.andromeda.data;

import java.io.Serializable;

/**
 * Created by Matt on 6/1/2016.
 */
public class LatLon implements Serializable, Comparable<LatLon> {

    private final double longitude;
    private final double latitude;

    public LatLon(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        LatLon other = (LatLon) obj;
        return Math.abs(latitude - other.latitude) < 0.00001
                && Math.abs(longitude - other.longitude) < 0.00001;
    }
    @Override
    public String toString() {
        return ", lat=" + latitude
                + "long=" + longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    @Override
    public int compareTo(LatLon another) {
        if (this.equals(another))
            return 0;
        else
            return (int) ((this.latitude*this.longitude) - (another.latitude*another.longitude));
    }
}
