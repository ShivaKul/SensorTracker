package com.example.shiva.sensortracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class Record extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    ArrayList<String> listTimeStamps = new ArrayList<>();
    ListView listView = null;

    static boolean firstTimeSpinner = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firstTimeSpinner = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        String[] spinnerItems = new String[] {
                "Login", "Sensors", "Record"
        };

        Spinner spinner = (Spinner) findViewById(R.id.screen_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(2);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, simple_list_item_1, listTimeStamps);
        listView = (ListView) findViewById(R.id.time_stamps);
        listView.setAdapter(adapter);

        Switch switch_r= (Switch) findViewById(R.id.switch_toggle);
        switch_r.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Long tsLong = System.currentTimeMillis() / 1000;
                    String ts = tsLong.toString();
                    if(listTimeStamps.size() > 4)
                        listTimeStamps.remove(0);
                    listTimeStamps.add(ts);
                }
                else{
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void goToRecord(View view){

    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if(firstTimeSpinner)
        {
            firstTimeSpinner = false;
        }
        else
        {
            Intent intent = null;
            if(parent.getItemAtPosition(pos).toString().equals("Login"))
                intent = new Intent(this, MainActivity.class);
            else if(parent.getItemAtPosition(pos).toString().equals("Sensors"))
                intent = new Intent(this, Sensors.class);
            else if(parent.getItemAtPosition(pos).toString().equals("Record"))
                intent = new Intent(this, Record.class);
            if(intent != null)
            {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
