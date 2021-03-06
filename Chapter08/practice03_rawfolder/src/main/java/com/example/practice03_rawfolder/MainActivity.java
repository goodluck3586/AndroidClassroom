package com.example.practice03_rawfolder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button btnInputStreamReader, btnBufferedReader;
    TextView textView;
    Long startTime, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInputStreamReader = findViewById(R.id.btnISR);
        btnBufferedReader = findViewById(R.id.btnBR);
        textView = findViewById(R.id.textView);

        // InputStreamReader를 이용한 파일 읽기
        btnInputStreamReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try(InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.test_text))){
                    startTime = System.currentTimeMillis();
                    int i;
                    StringBuilder sb = new StringBuilder();     // String 값을 수정할 수 있는 객체
                    while((i=isr.read()) != -1){                // byte = 1byte, -127~128, char = 2byte, int 4byte
                        sb.append((char)i);
                    }
                    endTime = System.currentTimeMillis();
                    Toast.makeText(MainActivity.this, String.format("%d ms", endTime-startTime), Toast.LENGTH_SHORT).show();
                    textView.setText(sb.toString());
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // BufferedReader를 이용한 파일 읽기
        btnBufferedReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try(BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.test_text)))){
                    startTime = System.currentTimeMillis();
                    int i;
                    StringBuilder sb = new StringBuilder();     // String 값을 수정할 수 있는 객체
                    while((i=br.read()) != -1){                // byte = 1byte, -127~128, char = 2byte, int 4byte
                        sb.append((char)i);
                    }
                    endTime = System.currentTimeMillis();
                    Toast.makeText(MainActivity.this, String.format("%d ms", endTime-startTime), Toast.LENGTH_SHORT).show();
                    textView.setText(sb.toString());
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }
}
