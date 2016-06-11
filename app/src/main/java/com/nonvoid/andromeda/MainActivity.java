package com.nonvoid.andromeda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nonvoid.andromeda.MVP.CheckHintActivity;
import com.nonvoid.andromeda.MVP.CreateHintActivity;
import com.nonvoid.andromeda.MVP.MapsActivity;
import com.nonvoid.andromeda.MVP.ViewHintsActivity;

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
            case R.id.buttonCreateHint :
                i = new Intent(this, CreateHintActivity.class);
                startActivity(i);
                break;
            case R.id.buttonViewHints :
                i = new Intent(this, ViewHintsActivity.class);
                startActivity(i);
                break;
        }
    }

    private void debug(String s){
        Log.d(MainActivity.DEBUGSTR, "MainActivity: " + s);
    }
}
