package com.example.exercise6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextClock textClock;
    Button btnShowTime;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textClock=findViewById(R.id.textClock);
        textView=findViewById(R.id.textview);
        btnShowTime=findViewById(R.id.btnGetTime);

        btnShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Time: "+textClock.getText());
            }
        });

    }
}
