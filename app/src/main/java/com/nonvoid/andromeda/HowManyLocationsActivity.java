package com.nonvoid.andromeda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HowManyLocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_locations);


    }

    public void onButtonClickValues(View view)
    {
        Intent startNextAcivity = new Intent(HowManyLocationsActivity.this, SubmittingLocationPointsActivity.class);
        startActivity(startNextAcivity);
    }

}
