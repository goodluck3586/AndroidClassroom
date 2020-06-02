package com.example.interface_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

// 인터페이스
interface Calc{
    int add(int num1, int num2);
    int substract(int num1, int num2);
}

// 추상클래스
abstract class AbstractCalc implements Calc{

    @Override
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public abstract int multiply(int num1, int num2);
    public abstract double divide(double num1, double num2);
}

// 추상클래스를 상속받은 클래스
class Calculator extends AbstractCalc{

    @Override
    public int substract(int num1, int num2) {
        return num1 - num2;
    }

    @Override
    public int multiply(int num1, int num2) {
        return num1 * num2;
    }

    @Override
    public double divide(double num1, double num2) {
        return num1 / num2;
    }
}

// 인터페이스를 구현한 클래스를 실행하는 Activity 클래스
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calculator calc = new Calculator();
        Log.d("Result", "덧셈 결과 : "+calc.add(10, 20));
        Log.d("Result", "뺄셈 결과 : "+calc.substract(10, 20));
        Log.d("Result", "곱셈 결과 : "+calc.multiply(10, 20));
        Log.d("Result", "나눗셈 결과 : "+calc.divide(10, 20));
    }
}
