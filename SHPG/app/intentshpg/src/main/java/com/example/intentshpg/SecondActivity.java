package com.example.intentshpg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

    TextView secondTextView;
    RadioButton runPhoneCall, runCamera;
    Button gotoMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        secondTextView = findViewById(R.id.secondTextView);
        runPhoneCall = findViewById(R.id.runPhoneCall);
        runCamera = findViewById(R.id.runCamera);
        gotoMainActivity = findViewById(R.id.goToMainActivity);

        // MainActivity로부터 넘겨받은 데이터 적용하기
        Intent recieveIntent = getIntent();
        String str = recieveIntent.getStringExtra("mainData");
        secondTextView.setText(str);

        // RadioButton 선택 여하에 따라 Intent에 다른 값 전달하기
        gotoMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getApplicationContext(), MainActivity.class);
                if(runPhoneCall.isChecked()) {
                    sendIntent.putExtra("secondData", 1);
                    setResult(RESULT_OK, sendIntent);
                } else if (runCamera.isChecked()) {
                    sendIntent.putExtra("secondData", 2);
                    setResult(RESULT_OK, sendIntent);
                } else
                    Toast.makeText(SecondActivity.this,
                            "라디오버튼을 선택하세요",
                            Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
