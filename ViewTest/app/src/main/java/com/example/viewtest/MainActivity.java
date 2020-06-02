package com.example.viewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // View 참조 변수 생성
    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button=findViewById(R.id.button);

        textView.setText("안녕하세요!!!");
    }

    public void displayString(View view) {
        if(editText.getText().toString().equals("")){
            Toast.makeText(this, "입력된 문자가 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        textView.setText(editText.getText());
    }
}
