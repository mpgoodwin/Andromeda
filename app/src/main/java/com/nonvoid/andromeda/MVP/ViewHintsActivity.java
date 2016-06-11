package com.nonvoid.andromeda.MVP;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.nonvoid.andromeda.R;

public class ViewHintsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hints);
    }

public void onSaveHintLocation (View view)
{



    //EDIT THIS INTENT TO START A DIFFERENT ACTIVITY
    Intent startNextAcivity = new Intent(ViewHintsActivity.this, MapsActivity.class);
    startActivity(startNextAcivity);
}


}
