package com.example.shiva.sensortracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static boolean firstTimeSpinner = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(myToolbar);
        Spinner spinner = (Spinner) findViewById(R.id.screen_spinner);
        spinner.setOnItemSelectedListener(this);

    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    boolean inputisOkay(){
        EditText contact_no = findViewById(R.id.contact);
        EditText email = findViewById((R.id.email));
        if(!validate(email.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Please enter valid e-mail address.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(contact_no.length() != 10)
        {
            Toast.makeText(getApplicationContext(), "Please enter 10 digit contact number.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Pattern.matches("[0-9]+", contact_no.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Please enter 10 digit contact number.", Toast.LENGTH_SHORT).show();
            return false;
        }
        RadioGroup radioGroup = findViewById(R.id.radio_gender);
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getApplicationContext(), "Please choose gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        EditText firstName = findViewById(R.id.first_name);
        EditText lastName = findViewById(R.id.last_name);
        EditText age = findViewById(R.id.age);
        if(firstName.getText().toString().length() == 0 || lastName.getText().toString().length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Name can't be left blank.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Pattern.matches("[0-9]+", age.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Age must be numeric.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
       if(firstTimeSpinner)
       {
           firstTimeSpinner = false;
       }
       else if(inputisOkay())
       {
           Intent intent = null;
           /*if(parent.getItemAtPosition(pos).toString().equals("Login"))
               intent = new Intent(this, MainActivity.class);
           else*/
           if(parent.getItemAtPosition(pos).toString().equals("Sensors"))
           {
               intent = new Intent(this, Sensors.class);
           }
           else if(parent.getItemAtPosition(pos).toString().equals("Record"))
               intent = new Intent(this, Record.class);
           if(intent != null)
           {
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);
               finish();
           }
       }
       else
       {
           parent.setSelection(0);
       }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    // male
                    break;
            case R.id.female:
                if (checked)
                    // female
                    break;
            case R.id.others:
                if(checked)
                    // others
                    break;
        }
    }

    public void goToRecord(View view) {
        if (inputisOkay()){
            Intent intent = new Intent(this, Record.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
