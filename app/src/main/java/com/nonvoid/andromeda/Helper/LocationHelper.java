package com.nonvoid.andromeda.helper;

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
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.nonvoid.andromeda.MainActivity;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class LocationHelper implements Serializable {

    private LocationManager locationManager;
    private Context context;

    public LocationHelper(LocationManager locationManager, Context context){
        this.locationManager = locationManager;
        this.context = context;
    }

    private void getAddressFromLocation(double latitude, double longitude, Context context){
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        String[] address;
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

    public LatLng getCurrentLatLng(){
        Log.d(MainActivity.DEBUGSTR, "LocationHelper.getCurrentLatLng()");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // permission granted

            String provider = locationManager.getBestProvider(new Criteria(), false);
            locationManager.requestLocationUpdates(provider, 400, 1, (LocationListener) context);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(provider);
                // Check if last known location set
                if (location != null) {
                    Toast.makeText(context, "Found stored current location!", Toast.LENGTH_LONG).show();
                    return new LatLng(location.getLatitude(), location.getLongitude());
                } else {
                    // if last location not already recorded, force location lookup
                    provider = locationManager.getBestProvider(new Criteria(), false);
                    locationManager.requestSingleUpdate(provider, (LocationListener) context, null);
                    location = locationManager.getLastKnownLocation(provider);
                    if (location != null) {
                        Toast.makeText(context, "Location current location!", Toast.LENGTH_LONG).show();
                        return new LatLng(location.getLatitude(), location.getLongitude());
                    } else {
                        Toast.makeText(context, "Location Not Found", Toast.LENGTH_LONG).show();
                        //return new LocationHelper(location, context);
                    }
                }
            }
        }
        //if it makes it here, error.
        Toast.makeText(context, "Location Not Found", Toast.LENGTH_LONG).show();
        return null;
    }
}
