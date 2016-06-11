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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.nonvoid.andromeda.R;
import com.nonvoid.andromeda.data.Hint;
import com.nonvoid.andromeda.data.HintList;
import com.nonvoid.andromeda.helper.LocationHelper;

public class CreateHintActivity extends AppCompatActivity implements LocationListener {

    LocationHelper locationHelper;
    Hint hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hint);

        locationHelper = new LocationHelper((LocationManager) getSystemService(Context.LOCATION_SERVICE), this);

    }

    public void onClick(View v){
        switch (v.getId()) {
        case  R.id.buttonGetLocation:
            hint = new Hint(locationHelper.getCurrentLatLng());
            break;
            case R.id.buttonSaveHint :
                EditText text = (EditText) findViewById(R.id.editTextHintText);
                hint.setText(text.getText().toString());
                if(hint.center != null && hint.getText().length() > 0){
                    HintList list = new HintList(this);
                    list.addUnique(hint);
                    list.save();
                }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
