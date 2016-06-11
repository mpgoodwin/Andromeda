package com.nonvoid.andromeda.MVP;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.nonvoid.andromeda.R;
import com.nonvoid.andromeda.data.Hint;
import com.nonvoid.andromeda.data.HintList;
import com.nonvoid.andromeda.io.InternalStorage;

import java.util.ArrayList;

public class ViewHintsActivity extends AppCompatActivity {



    HintListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hints);

        //ArrayList<Hint> hints = InternalStorage.readHintList(this);

        ArrayList<Hint> list = new ArrayList<Hint>();

        if(list.isEmpty())
        {
            //display in short period of time
            Toast.makeText(getApplicationContext(), "No hints have been made yet.",
                    Toast.LENGTH_LONG).show();
        }

        else
        // Display the items from the list retrieved.
        for (Hint singleHunt : list)
        {
            // LOG Entries here

        }

        ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new HintListViewAdapter(this,list);

        // connect the arraylist of exercises to the listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        Intent intent = new Intent(ViewHintsActivity.this, CheckHintActivity.class);
                        startActivityForResult(intent, position);

                    }
                }

        );
    }




    public class HintListViewAdapter extends ArrayAdapter<Hint> {
        public HintListViewAdapter(Context context, ArrayList<Hint> hints) {
            super(context, 0);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Hint onehint = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_check_hint, parent, false);

            }


            // Lookup view for data population
            TextView HintDescription = (TextView) convertView.findViewById(R.id.textView6);
            // Populate the data into the template view using the data object

            HintDescription.setText(onehint.getText());

            // Return the completed view to render on screen
            return convertView;
        }
    }




}
