package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    // View 참조 변수 선언
    EditText editTextNum1, editTextNum2;
    Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    TextView textViewResult;
    double num1, num2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        textViewResult = findViewById(R.id.textViewResult);

        btnAdd.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(checkNum()){
                    return;
                }
                result=num1+num2;
                textViewResult.setText("덧셈 계산 결과 : " + result);
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(checkNum()){
                    return;
                }
                result=num1-num2;
                textViewResult.setText("뺄셈 계산 결과 : " + result);
            }
        });


    }

    private boolean checkNum(){
        textViewResult.setText("계산 결과 : ");
        if(editTextNum1.getText().toString().equals("") || editTextNum2.getText().toString().equals("")){
            Toast.makeText(this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            num1 = Double.parseDouble(editTextNum1.getText().toString());
            num2 = Double.parseDouble(editTextNum2.getText().toString());
            return false;
        }
    }
}
