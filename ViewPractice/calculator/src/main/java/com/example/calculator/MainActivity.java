package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //region 1. View 참조변수 선언
    EditText editText1, editText2;
    Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    TextView textView_result;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region 2. View 참조변수에 View 연결
        editText1 = findViewById(R.id.editTextNum1);
        editText2 = findViewById(R.id.editTextNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        textView_result = findViewById(R.id.textViewResult);
        //endregion

        //region 3. 이벤트 리스너 객체 생성
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(checkNumber())
                    return;
                double num1, num2;
                num1 = Double.parseDouble(editText1.getText().toString());
                num2 = Double.parseDouble(editText2.getText().toString());

                switch (v.getId()) {
                    case R.id.btnAdd:
                        textView_result.setText(String.format("덧셈 결과: %.1f + %.1f = %.1f", num1, num2, num1+num2));
                        break;
                    case R.id.btnSubtract:
                        textView_result.setText(String.format("뺄셈 결과: %.1f - %.1f = %.1f", num1, num2, num1-num2));
                        break;
                    case R.id.btnMultiply:
                        textView_result.setText(String.format("곱셈 결과: %.1f * %.1f = %.1f", num1, num2, num1*num2));
                        break;
                    case R.id.btnDivide:
                        textView_result.setText(String.format("나눗셈 결과: %.1f / %.1f = %.1f", num1, num2, num1/num2));
                        break;
                }
            }
        };
        //endregion

        //region 4. 버튼 참조변수에 이벤트 리스너 연결
        btnAdd.setOnClickListener(listener);
        btnSubtract.setOnClickListener(listener);
        btnMultiply.setOnClickListener(listener);
        btnDivide.setOnClickListener(listener);
        //endregion
    }

    // TextBox에 값이 입력되었는지 체크하는 함수
    private boolean checkNumber() {
        textView_result.setText("계산 결과 : ");
        if (editText1.getText().toString().equals("") || editText2.getText().toString().equals("")) {
            Toast.makeText(this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
