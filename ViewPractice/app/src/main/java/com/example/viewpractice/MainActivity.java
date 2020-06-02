package com.example.viewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // View 참조 변수 선언
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button1);
        button.setText("버튼입니다.");

    }

    public void showToastTest(View view) {
        Toast.makeText(this, "버튼1이 클릭됨.", Toast.LENGTH_SHORT).show();
    }
}
