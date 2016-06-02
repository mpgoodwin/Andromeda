package com.nonvoid.andromeda.Helper;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Parcel;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class LocationHelper implements Serializable {
    byte[] location;
    double latitude, longitude;
    String[] address;

    public LocationHelper(){}
    public LocationHelper(Location location, Context context){
        setLocation(location, context);
    }

    public Location getLocation() {
        Location targetLocation = new Location("");//provider name is unecessary
        targetLocation.setLatitude(latitude);//your coords of course
        targetLocation.setLongitude(longitude);
        return targetLocation;
    }

    public void setLocation(Location location, Context context) {
        Parcel p = Parcel.obtain();
        location.writeToParcel(p, 0);
        this.location = p.marshall();
        p.recycle();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        getAddressFromLocation(latitude, longitude, context);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String[] getAddress() {
        return address;
    }

    private void getAddressFromLocation(double latitude, double longitude, Context context){
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if(addresses != null && addresses.size() > 0) {
                Address returnedAddress = addresses.get(0);
                address = new String[returnedAddress.getMaxAddressLineIndex()];
                for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                    address[i] = returnedAddress.getAddressLine(i);
                }
            }
            else{
                address = new String[] {"Cannot find location"};
            }
        } catch (IOException e) {
            e.printStackTrace();
            address =new String[] {"Cannot find location"};
        }

    }

    public static LocationHelper getCurrentLocation(LocationManager locationManager, Context context){
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // permission granted

            String provider = locationManager.getBestProvider(new Criteria(), false);
            locationManager.requestLocationUpdates(provider, 400, 1, (LocationListener) context);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {            }
            Location location = locationManager.getLastKnownLocation(provider);
            // Check if last known location set
            if (location != null) {
                Toast.makeText(context, "Found stored current location!", Toast.LENGTH_LONG).show();
                return new LocationHelper(location, context);
            } else {
                // if last location not already recorded, force location lookup
                provider = locationManager.getBestProvider(new Criteria(), false);
                locationManager.requestSingleUpdate(provider, (LocationListener) context, null);
                location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    Toast.makeText(context, "Location current location!", Toast.LENGTH_LONG).show();
                    //return new LocationHelper(location, context);
                } else {
                    Toast.makeText(context, "Location Not Found", Toast.LENGTH_LONG).show();
                    //return new LocationHelper(location, context);
                }
            }
        }
        //if it makes it here, error.
        return null;
    }
}
