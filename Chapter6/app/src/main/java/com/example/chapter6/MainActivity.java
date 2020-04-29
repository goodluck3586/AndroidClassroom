package com.example.chapter6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
//        setContentView(R.layout.activity_main);
        setContentView(view1);

        textView = findViewById(R.id.textView);
        datePicker = findViewById(R.id.datePicker);

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });
    }
}
