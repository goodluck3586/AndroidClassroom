package com.example.exercise6_13_scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linear1 = findViewById(R.id.linear1);
        for (int i=1; i<=50; i++){
            Button button = new Button(this);
            button.setTextSize(30);
            button.setText("버튼 " + i);
            linear1.addView(button);
        }
    }
}

