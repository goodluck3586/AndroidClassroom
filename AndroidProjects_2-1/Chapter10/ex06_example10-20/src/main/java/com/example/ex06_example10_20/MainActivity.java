package com.example.ex06_example10_20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDial, btnWeb, btnGoogleMap, btnSearch, btnSms, btnPhoto, btnIntentTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDial = findViewById(R.id.btnDial);
        btnWeb = findViewById(R.id.btnWeb);
        btnGoogleMap = findViewById(R.id.btnGoogleMap);
        btnSearch = findViewById(R.id.btnSearch);
        btnSms = findViewById(R.id.btnSms);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnIntentTest = findViewById(R.id.btnMyIntentFilter);

        View.OnClickListener listener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (v.getId()){
                    case R.id.btnDial:
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"));
//                        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01012345678"));
                        break;
                    case R.id.btnWeb:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                        break;
                    case R.id.btnGoogleMap:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/LYSGS7KJHLG4nXBt5"));
                        break;
                    case R.id.btnSearch:
                        intent = new Intent(Intent.ACTION_SEARCH);
                        intent.putExtra(SearchManager.QUERY, "광주소프트웨어마이스터고");
                        break;
                    case R.id.btnSms:
                        intent = new Intent(Intent.ACTION_SENDTO);
                        intent.putExtra("sms_body", "안녕");
                        intent.setData(Uri.parse("smsto:"+Uri.encode("010-1234-5678")));
                        break;
                    case R.id.btnPhoto:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        break;
                }
                startActivity(intent);
            }
        };

        btnDial.setOnClickListener(listener);
        btnWeb.setOnClickListener(listener);
        btnGoogleMap.setOnClickListener(listener);
        btnSearch.setOnClickListener(listener);
        btnSms.setOnClickListener(listener);
        btnPhoto.setOnClickListener(listener);

        // 사용자가 설정한 암시적 인텐트 필터 테스트
        btnIntentTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.ex06_example10_20.ACTION_VIEW");
                startActivity(intent);
            }
        });
    }
}
