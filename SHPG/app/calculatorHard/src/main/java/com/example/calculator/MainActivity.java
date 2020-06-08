package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView process, result;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnPlus, btnMinus, btnMul, btnDivide, btnClear, btnResult;
    float num1, num2;
    char lastSymbol;        // 연산의 종류를 판단하기 위해 하나의 문자열을 저장할 변수
    boolean isHaveNum;      // 숫자의 입력이 존재할 경우(연산을 시작할 숫자가 존재하는 경우) true
    boolean isInputingNumber;       // Button 위젯으로 입력받는 String이 연속되는 숫자인지 판별

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        process = findViewById(R.id.process);
        result = findViewById(R.id.result);
        btn0 = findViewById(R.id.btn0); btn1 = findViewById(R.id.btn1); btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3); btn4 = findViewById(R.id.btn4); btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6); btn7 = findViewById(R.id.btn7); btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus); btnMinus = findViewById(R.id.btnMinus);
        btnMul = findViewById(R.id.btnMul); btnDivide = findViewById(R.id.btnDivide);
        btnClear = findViewById(R.id.btnClear); btnResult = findViewById(R.id.btnResult);

        process.setText(""); result.setText("");

        // 숫자 버튼들에 동작하는 리스너 객체 생성 및 연결
        NumberClickListener numberClickListener = new NumberClickListener();
        btn0.setOnClickListener(numberClickListener); btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener); btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener); btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener); btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener); btn9.setOnClickListener(numberClickListener);

        // 기호 버튼들에 동작하는 리스너 객체 생성 및 연결
        SymbolClickListener symbolClickListener = new SymbolClickListener();
        btnPlus.setOnClickListener(symbolClickListener); btnMinus.setOnClickListener(symbolClickListener);
        btnMul.setOnClickListener(symbolClickListener); btnDivide.setOnClickListener(symbolClickListener);
        btnClear.setOnClickListener(symbolClickListener); btnResult.setOnClickListener(symbolClickListener);

    }

    // 숫자 버튼들에 동작하는 리스너 정의
    class NumberClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(lastSymbol == 'r') {
                Toast.makeText(MainActivity.this, "Clear 후 다시 계산해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            if(result.getText().toString().equals("0"))
                result.setText("");
            switch (v.getId()) {
                case R.id.btn0:
                    result.append("0");
                    break;
                case R.id.btn1:
                    result.append("1");
                    break;
                case R.id.btn2:
                    result.append("2");
                    break;
                case R.id.btn3:
                    result.append("3");
                    break;
                case R.id.btn4:
                    result.append("4");
                    break;
                case R.id.btn5:
                    result.append("5");
                    break;
                case R.id.btn6:
                    result.append("6");
                    break;
                case R.id.btn7:
                    result.append("7");
                    break;
                case R.id.btn8:
                    result.append("8");
                    break;
                case R.id.btn9:
                    result.append("9");
                    break;
            }
            isInputingNumber = true;    // 숫자가 연속해서 입력받는 동안 true로 셋팅
        }
    }

    class SymbolClickListener implements  View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnClear) {
                process.setText("");
                result.setText("");
                num1 = 0; num2 = 0; isHaveNum = false; lastSymbol = 'x';
                return;
            }       // 클리어 버튼을 눌렀을 때. 계산에 필요한 정보들 초기화
            if(v.getId() == R.id.btnResult) {
                if(isInputingNumber == true) {
                    Toast.makeText(MainActivity.this, "수식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }   // 1. 수식의 마지막이 숫자값인지 확인함
                if(lastSymbol == 'r')               // 2. 계산이 완료된 경우 반응하지 않게 함
                    return;
                process.append(result.getText().toString() + " = ");
                switch (lastSymbol) {
                    case '+':
                        num1 += Float.valueOf(result.getText().toString());
                        break;
                    case '-':
                        num1 -= Float.valueOf(result.getText().toString());
                        break;
                    case '*':
                        num1 *= Float.valueOf(result.getText().toString());
                        break;
                    case '/':
                        num1 /= Float.valueOf(result.getText().toString());
                        break;
                }           // 3. 마지막에 입력된 기호에 따라 마지막 항의 계산을 함.
                result.setText(String.valueOf(num1));
                lastSymbol = 'r';                   // 4. 심볼값을 'r' (result) 로 설정함.

                return;
            }   // '=' 버튼을 눌렀을 때.
            if(lastSymbol == 'r') {
                Toast.makeText(MainActivity.this, "Clear 후 다시 계산해주세요", Toast.LENGTH_SHORT).show();
                return;
            }   // 계산이 완료된 경우 수식을 초기화하도록 안내함
            if(isInputingNumber == false) {
                Toast.makeText(MainActivity.this, "기호를 연속으로 입력하지 마세요", Toast.LENGTH_SHORT).show();
                return;
            }   // 기호를 연속으로 입력하지 않도록 방지함
            if(isHaveNum == false) {
                num1 = Float.valueOf(result.getText().toString());
                switch (v.getId()) {
                    case R.id.btnPlus:
                        lastSymbol = '+';
                        process.append(result.getText().toString() + " + ");
                        result.setText("");
                    break;
                    case R.id.btnMinus:
                        lastSymbol = '-';
                        process.append(result.getText().toString() + " - ");
                        result.setText("");
                        break;
                    case R.id.btnMul:
                        lastSymbol = '*';
                        process.append(result.getText().toString() + " * ");
                        result.setText("");
                        break;
                    case R.id.btnDivide:
                        lastSymbol = '/';
                        process.append(result.getText().toString() + " / ");
                        result.setText("");
                        break;
                }
                isHaveNum = true;
            }   // 숫자를 처음 입력받는 경우 숫자값 및 연산자 저장.
            else {
                switch (lastSymbol) {
                    case '+':
                        num2 = Float.valueOf(result.getText().toString());
                        num1 = num1 + num2;
                        break;
                    case '-':
                        num2 = Float.valueOf(result.getText().toString());
                        num1 = num1 - num2;
                        break;
                    case '*':
                        num2 = Float.valueOf(result.getText().toString());
                        num1 = num1 * num2;
                        break;
                    case '/':
                        num2 = Float.valueOf(result.getText().toString());
                        num1 = num1 / num2;
                        break;
                }   // 다항 연산을 할 경우 이전 항의 값을 계산하여 반영함.
                switch (v.getId()) {
                    case R.id.btnPlus:
                        lastSymbol = '+';
                        process.append(result.getText().toString() + " + ");
                        result.setText("");
                        break;
                    case R.id.btnMinus:
                        lastSymbol = '-';
                        process.append(result.getText().toString() + " - ");
                        result.setText("");
                        break;
                    case R.id.btnMul:
                        lastSymbol = '*';
                        process.append(result.getText().toString() + " * ");
                        result.setText("");
                        break;
                    case R.id.btnDivide:
                        lastSymbol = '/';
                        process.append(result.getText().toString() + " / ");
                        result.setText("");
                        break;
                }   // 다항 연산을 할 경우 추후 연산을 위해 기호를 저장함
                    // 해당 switch문이 없을 경우 단항 연산만 가능함
            }   // 다항 연산을 위해 새로 입력받은 값을 계산하고 기호를 저장함
            isInputingNumber = false;     // 문자열을 입력하면 false로 셋팅하여 기호 중복 입력 방지
        }
    }
}