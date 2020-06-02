package com.example.eventpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// View.onClickListener 인터페이스를 구현한 클래스
class MyListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        Log.d("MyListener", "버튼2가 클릭되었습니다.");
        Toast.makeText(v.getContext(), "버튼2가 클릭되었습니다.", Toast.LENGTH_SHORT).show();
    }
}

public class MainActivity extends AppCompatActivity {

    // 버튼 참조변수 생성
    Button btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // View를 메모리 상의 객체로 만들어줌.

        // 참조변수에 버튼 객체 연결
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);

        // 1. View.onClickListener를 구현한 클래스를 이용
        MyListener myListener = new MyListener();
        btn2.setOnClickListener(myListener);

        // 2. View.onClickListener의 익명 클래스를 생성하여 참조변수에 할당
        View.OnClickListener listener2 = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "버튼3가 클릭됨.", Toast.LENGTH_SHORT).show();
            }
        };
        btn3.setOnClickListener(listener2);

        // 3. 리스너를 구현한 익명 클래스를 setOnClickListener()의 매개변수로 전달
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "버튼4가 클릭됨.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onButtonClicked(View view) {
        Toast.makeText(this, "버튼1 클릭됨.", Toast.LENGTH_SHORT).show();
    }
}
