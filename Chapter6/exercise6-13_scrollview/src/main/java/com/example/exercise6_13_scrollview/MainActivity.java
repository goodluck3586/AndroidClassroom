package com.example.exercise6_13_scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);

        for (int i=0; i<50; i++){
            Button button = new Button(this);
            button.setTextSize(30);
            button.setText("버튼 "+i);
            linearLayout.addView(button);
        }




    }
}
