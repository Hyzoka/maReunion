package com.ocr.mareunion;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class addMeeting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TimePicker picker;
    ArrayList<Meeting> meetings  = new ArrayList();
    Button btnAdd;
    EditText sujetEdit,mailEdit;
    String[] country = {"Mario","Luigi","Peach","Toad"};
    String salle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        sujetEdit=(EditText)findViewById(R.id.etsujet);
        mailEdit=(EditText)findViewById(R.id.etMail);
        picker=(TimePicker)findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);
        btnAdd=(Button)findViewById(R.id.addMeeting);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int hour, minute;
                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = picker.getHour();
                    minute = picker.getMinute();
                }
                else{
                    hour = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();
                }

                Intent intent = new Intent(addMeeting.this,MainActivity.class);
                intent.putExtra("salle",salle);
                intent.putExtra("heure",hour);
                intent.putExtra("min",String.valueOf(minute));
                intent.putExtra("sujet",sujetEdit.getText().toString());
                intent.putExtra("mail",mailEdit.getText().toString());
                setResult(2,intent);
                finish();

            }
        });

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        salle=country[position];

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}