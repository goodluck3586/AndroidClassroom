package com.example.ex05_diy10_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextNum1, editTextNum2;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        btnCalculate = findViewById(R.id.btnCalculate);

        // 더하기 버튼을 클릭하면, 입력된 두 숫자를 Intent 객체에 담아서 SecondActivity로 보내기
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("Num1", Integer.parseInt(editTextNum1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(editTextNum2.getText().toString()));
                startActivityForResult(intent, 0);
            }
        });
    }

    // startActivityForResult()의 결과가 돌아오면 자동 호출되는 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode==RESULT_OK){
            int hap = data.getIntExtra("Hap", 0);
            Toast.makeText(this, "합계" + hap, Toast.LENGTH_SHORT).show();
        }
    }
}
