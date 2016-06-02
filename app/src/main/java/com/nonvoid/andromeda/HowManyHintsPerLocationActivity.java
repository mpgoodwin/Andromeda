package com.nonvoid.andromeda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HowManyHintsPerLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_hints_per_location);
    }


    public void onButtonClickHintValues(View view)
    {
        Intent startNextAcivity = new Intent(HowManyHintsPerLocationActivity.this, SubmittingAHintPerLocationActivity.class);
        startActivity(startNextAcivity);
    }

}
