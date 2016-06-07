package com.nonvoid.andromeda.MVP;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.nonvoid.andromeda.MainActivity;
import com.nonvoid.andromeda.R;
import com.nonvoid.andromeda.data.Location;
import com.nonvoid.andromeda.helper.LocationHelper;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, android.location.LocationListener, GoogleMap.OnMapClickListener{

    private GoogleMap mMap;

    private Location currentLocation;
    private Location clickedLocation;
    private LocationHelper locationHelper;
    //private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        debug("onCreate start");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setupLocationHelper();
        getCurrentLocation();
        debug("Current Location");
        debug(currentLocation.toString());
    }

    public void getCurrentLocation(){
        currentLocation = new Location(locationHelper.getCurrentLatLng());
    }

    public void setupLocationHelper(){
        locationHelper = new LocationHelper((LocationManager) getSystemService(Context.LOCATION_SERVICE), this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        debug("mapReady: start");
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);
        debug("setMyLocation true");

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(currentLocation.center).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation.center));


    }

    private void debug(String s){
        Log.d(MainActivity.DEBUGSTR, "MapsActivity: " + s);
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        //currentLocation.setCenter( locationHelper.getCurrentLatLng());
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    public void onProviderEnabled(String provider) {

    }
    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        debug("onMapClick start.");
        mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked"));
    }
}
