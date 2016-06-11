package com.nonvoid.andromeda.MVP;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.nonvoid.andromeda.R;
import com.nonvoid.andromeda.helper.LocationHelper;

public class CreateHintActivity extends AppCompatActivity implements LocationListener {

    LocationHelper locationHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hint);

        locationHelper = new LocationHelper((LocationManager) getSystemService(Context.LOCATION_SERVICE), this);

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
