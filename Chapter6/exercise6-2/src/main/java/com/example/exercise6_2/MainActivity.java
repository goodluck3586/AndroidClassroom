package com.example.exercise6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);

        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
        findViewById(R.id.btnReset).setOnClickListener(this);

        Log.d("chronometer.getBase()", String.valueOf(chronometer.getBase()));

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                Log.d("chronometer.getBase()", String.valueOf(SystemClock.elapsedRealtime()));
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnStart:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.btnStop:
                chronometer.stop();
                break;
            case R.id.btnReset:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                break;
        }
    }
}
