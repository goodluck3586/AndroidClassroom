package com.example.ex05_diy10_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnReturn = findViewById(R.id.btnReturn);

        // 인텐트 받아서, 데이터 꺼내기
        Intent inIntent = getIntent();
        final int hap = inIntent.getIntExtra("Num1", 0)
                + inIntent.getIntExtra("Num2", 0);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("Hap", hap);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
