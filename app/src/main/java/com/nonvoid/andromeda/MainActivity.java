package com.nonvoid.andromeda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String DEBUGSTR = "DebugLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        debug("Start");
    }

    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.buttonMaps :
                i = new Intent(this, MapsActivity.class);
                startActivity(i);
                break;
            case R.id.buttonCreateEvent :
                i = new Intent(this, CreateEventActivity.class);
                startActivity(i);
                break;
        }
    }

    private void debug(String s){
        Log.d(MainActivity.DEBUGSTR, "MainActivity: " + s);
    }
}
