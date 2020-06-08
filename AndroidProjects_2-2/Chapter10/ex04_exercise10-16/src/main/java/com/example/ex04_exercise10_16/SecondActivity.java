package com.example.ex04_exercise10_16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textViewCalc = findViewById(R.id.textViewCalc);

        // MainActivity가 보낸 Intent에서 데이터 꺼내기
        Intent inIntent = getIntent();
        int num1 = inIntent.getIntExtra("Num1", 0);
        int num2 = inIntent.getIntExtra("Num2", 0);
        final int hap = num1 + num2;

        // 화면세 계산식 표시
        textViewCalc.setText(String.format("%d + %d = %d", num1, num2, hap));

        // 돌아가기 버튼 클릭 이벤트 처리
        findViewById(R.id.btnReturn).setOnClickListener(new View.OnClickListener() {
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
