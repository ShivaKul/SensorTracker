package com.example.shiva.sensortracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class Sensors extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static boolean firstTimeSpinner = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firstTimeSpinner = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(myToolbar);
        Spinner spinner = (Spinner) findViewById(R.id.screen_spinner);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(this);
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
            {
                intent = new Intent(this, MainActivity.class);
            }
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

    public void goToRecord(View view){
        Intent intent = new Intent(this, Record.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
