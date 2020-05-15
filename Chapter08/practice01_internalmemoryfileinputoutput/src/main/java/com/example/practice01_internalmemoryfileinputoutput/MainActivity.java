package com.example.practice01_internalmemoryfileinputoutput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btnWrite, btnRead;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        textView = findViewById(R.id.textView);

        //region 내장 메모리 파일 입출력 처리1 (FileOutputStream, FileInputStream)
        View.OnClickListener listener1 = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnWrite:
                        FileOutputStream fos = null;
                        try {
                            // 파일 생성(없으면) 또는 연결(있으면)
//                            fos = new FileOutputStream(getFileStreamPath("myFile.txt"), false);
                            fos = openFileOutput("myFile.txt", Context.MODE_PRIVATE);

                            String string = editText.getText().toString();  // 입력된 문자열을 변수에 저장
                            fos.write(string.getBytes());                   // 입력된 문자열을 바이트 배열로 변환하여 파일에 쓰기

                            Toast.makeText(MainActivity.this, "myFile.txt에 문자열 써짐", Toast.LENGTH_SHORT).show();
                            editText.setText(null);
                        } catch (IOException e){
                            e.printStackTrace();
                        } finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;

                    case R.id.btnRead:
                        try(FileInputStream fis = new FileInputStream(getFileStreamPath("myFile.txt"))) {
//                            textView.setText(String.valueOf((char)fis.read()));     // 한 문자만 가져온다.

//                            // 한 바이트씩 모두 읽어오기
//                            String str = "";
//                            int temp;
//                            while((temp=fis.read()) != -1){        // fis.read()는 더이상 읽어올 데이터가 없으면 -1을 반환한다.
//                                str += (char)temp;
//                            }
//                            textView.setText(str);

                            // 바이트 배열 단위로 읽어오기
                            byte[] byteArray = new byte[20];
                            fis.read(byteArray);
                            textView.setText(new String(byteArray));

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
        //endregion

        //region 내장 메모리 파일 입출력 처리 2 (OutputStreamWriter, InputStreamReader)
        // 파일에서 바이트 단위로 읽은 자료를 문자 단위로 변환해주는 보조 스트림
        View.OnClickListener listener2 = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnWrite:
                        try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("myFile.txt", Context.MODE_PRIVATE))) {
                            outputStreamWriter.write(editText.getText().toString());
                            Toast.makeText(MainActivity.this, "myFile.txt 작성 완료", Toast.LENGTH_SHORT).show();
                            editText.setText(null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case R.id.btnRead:
                        try(InputStreamReader inputStreamReader = new InputStreamReader(openFileInput("myFile.txt"))) {
                            String str = "";
                            int temp;
                            while((temp = inputStreamReader.read()) != -1){
                                str += (char)temp;
                            }
                            textView.setText(str);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
        //endregion

        // 내장 메모리 파일 입출력 처리 3 (BufferedWriter, BufferedReader)

        btnWrite.setOnClickListener(listener2);
        btnRead.setOnClickListener(listener2);
    }
}
