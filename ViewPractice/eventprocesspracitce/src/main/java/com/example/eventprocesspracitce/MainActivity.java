package com.example.eventprocesspracitce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// View.OnClickListener를 구현한 클래스
class MyListenerClass implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        Log.d("ListenerClass", "ListenerClass");
        Toast.makeText(v.getContext(), "버튼1 클릭됨.", Toast.LENGTH_SHORT).show();
    }
}

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Button 객체 생성
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        //endregion

        //System.out.println("Hello World");

        //region 1. View.OnClickListener를 구현한 클래스 이용
        MyListenerClass listener1 = new MyListenerClass();  // 이벤트 리스너 객체 생성 (View.OnClickListener를 구현한 객체)
        btn1.setOnClickListener(listener1);             // 이벤트 리스너 등록
        //endregion

        //region 2. View.OnClickListener 익명 클래스를 생성하여 인터페이스 참조변수에 할당
        // 익명 내부 클래스는 단 하나의 인터페이스 또는 추상 클래스를 바로 생성할 수 있다.
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "버튼2 클릭됨.", Toast.LENGTH_SHORT).show();
            }
        };

        btn2.setOnClickListener(listener2);
        //endregion

        //region 3. 익명 내부 클래스를 setOnClickListener()의 매개변수로 전달
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "버튼3 클릭됨.", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion
    }

    public void OnButtonClicked(View view) {
        Toast.makeText(this, "버튼 0 클릭됨.", Toast.LENGTH_SHORT).show();
    }
}
