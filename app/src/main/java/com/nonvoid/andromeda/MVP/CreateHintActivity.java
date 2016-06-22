package com.nonvoid.andromeda.MVP;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.nonvoid.andromeda.MainActivity;
import com.nonvoid.andromeda.R;
import com.nonvoid.andromeda.data.Hint;
import com.nonvoid.andromeda.helper.LocationHelper;

public class CreateHintActivity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient = null;
    Location mLastLocation;
    LocationHelper locationHelper;
    Hint hint;

    TextView mLatitude;
    TextView mLongitude;

    boolean savedLatLng = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hint);
        //For Location Services
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mLatitude = (TextView) findViewById(R.id.textViewLatitude);
        mLongitude = (TextView) findViewById(R.id.textViewLongitude);
    }

    public void onClick(View v) {
        Log.d(MainActivity.DEBUGSTR, "Create hint onClick method : " + ((Button) v).getText());
        switch (v.getId()) {
            case R.id.buttonGetLocation:
                hint = new Hint( );
                break;
            case R.id.buttonSaveHint:
               Hint newHint = new Hint();
                /*
                Log.d(MainActivity.DEBUGSTR, text.getText().toString());
                if (hint.center != null && text.getText().length() > 0) {

                    //HintList list = new HintList(this);
                    ArrayList<Hint> list = InternalStorage.readHintList(this);
                    list.add(hint);
                    InternalStorage.writeHintsList(this, list);
                    Log.d(MainActivity.DEBUGSTR, "Finished saving hint");
                    finish();
                }
                */
                break;
        }
    }

    private LatLng getCurrentLocation() {
        Log.d(MainActivity.DEBUGSTR, "getCurrentLocation: start");
        double lat, lng;
        lat = Double.parseDouble( mLatitude.getText().toString());
        lng = Double.parseDouble( mLongitude.getText().toString());
        Log.d(MainActivity.DEBUGSTR, "lat: " +lat);
        Log.d(MainActivity.DEBUGSTR, "lng: "+lng);
        LatLng latLng = new LatLng(lat, lng);
        Log.d(MainActivity.DEBUGSTR, "getCurrentLocation: latLng: " + latLng.toString());
        return latLng;
        //tried this....
        //Location location = LocationHelper.getCurrentLocation( (LocationManager) getSystemService(Context.LOCATION_SERVICE) ,this);
        //Log.d(MainActivity.DEBUGSTR, "Got location");
        //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //Log.d(MainActivity.DEBUGSTR, latLng.toString());
        //locationHelper = new LocationHelper((LocationManager) getSystemService(Context.LOCATION_SERVICE), this);
        //hint = new Hint(locationHelper.getCurrentLatLng());
        //Log.d(MainActivity.DEBUGSTR, "LatLng: " + hint.center.toString());
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(MainActivity.DEBUGSTR, "onConnected");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d(MainActivity.DEBUGSTR, "onConnected failed self permissions");
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitude.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitude.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
