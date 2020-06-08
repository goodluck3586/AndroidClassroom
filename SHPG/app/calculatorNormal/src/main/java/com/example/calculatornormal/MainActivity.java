package com.example.calculatornormal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView num1Text, num2Text;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button add, sub, mul, div, cal;
    TextView resultText;

    int flow = 0;
    // 작동 가능한 버튼들의 순서를 판단하기
    // 숫자1 입력 받기: 0
    // 숫자 1 입력 중(완료): 1
    // 숫자 1이 존재할 경우(flow == 1) 연산자 입력 가능 (flow: 1 -> 2)
    // 숫자 2 입력받기: 2
    // 숫자 2 입력 중(완료): 3
    // 숫자 2도 존재할 경우(flow == 3) 계산버튼 동작 flow: 3 -> 4

    char symbol;
    // 더하기, 빼기, 곱하기, 나누기를 구분하는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1Text = findViewById(R.id.num1Text);
        num2Text = findViewById(R.id.num2Text);

        btn0 = findViewById(R.id.btn0); btn1 = findViewById(R.id.btn1); btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3); btn4 = findViewById(R.id.btn4); btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6); btn7 = findViewById(R.id.btn7); btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        add = findViewById(R.id.add);   sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);   div = findViewById(R.id.div);
        cal = findViewById(R.id.cal);

        resultText = findViewById(R.id.resultText);

        // ButtonListener 등록하기
        NumberClickListener numberClickListener = new NumberClickListener();
        btn0.setOnClickListener(numberClickListener); btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener); btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener); btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener); btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener); btn9.setOnClickListener(numberClickListener);

        // symbolListener 등록하기
        SymbolListener symbolListener = new SymbolListener();
        add.setOnClickListener(symbolListener);
        sub.setOnClickListener(symbolListener);
        mul.setOnClickListener(symbolListener);
        div.setOnClickListener(symbolListener);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flow == 3) {
                    float num1 = Float.valueOf(num1Text.getText().toString());
                    float num2 = Float.valueOf(num2Text.getText().toString());
                    switch(symbol) {
                        case '+':
                            resultText.append(String.valueOf(num1 + num2));
                            break;
                        case '-':
                            resultText.append(String.valueOf(num1 - num2));
                            break;
                        case '*':
                            resultText.append(String.valueOf(num1 * num2));
                            break;
                        case '/':
                            resultText.append(String.valueOf(num1 / num2));
                            break;
                    }
                    flow = 4;
                }
            }
        });
        cal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                num1Text.setText("");
                num2Text.setText("");
                resultText.setText("계산 결과: ");
                flow = 0;
                return true;
            }
        });
    }

    // 숫자 버튼들에 동작하는 리스너 정의
    class NumberClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // flow가 0일때는 숫자1에, flow가 2일때는 숫자2에 입력
            TextView targetID;
            if (flow <= 1)
                targetID = findViewById(R.id.num1Text);
            else
                targetID = findViewById(R.id.num2Text);

            if (targetID.getText().toString().equals("0")) {
                targetID.setText("");
            }
            switch (v.getId()) {
                case R.id.btn0:
                    targetID.append("0");
                    break;
                case R.id.btn1:
                    targetID.append("1");
                    break;
                case R.id.btn2:
                    targetID.append("2");
                    break;
                case R.id.btn3:
                    targetID.append("3");
                    break;
                case R.id.btn4:
                    targetID.append("4");
                    break;
                case R.id.btn5:
                    targetID.append("5");
                    break;
                case R.id.btn6:
                    targetID.append("6");
                    break;
                case R.id.btn7:
                    targetID.append("7");
                    break;
                case R.id.btn8:
                    targetID.append("8");
                    break;
                case R.id.btn9:
                    targetID.append("9");
                    break;
            }
            if(flow == 0) flow = 1;
            else if(flow == 2) flow = 3;
        }
    }

    class SymbolListener implements  View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(num1Text.getText().toString().equals(""))
                return;
            if(flow != 1 && flow != 2)
                return;
            switch (v.getId()) {
                case R.id.add:
                    symbol = '+';
                    break;
                case R.id.sub:
                    symbol = '-';
                    break;
                case R.id.mul:
                    symbol = '*';
                    break;
                case R.id.div:
                    symbol = '/';
                    break;
            }
            flow = 2;
        }
    }
}
