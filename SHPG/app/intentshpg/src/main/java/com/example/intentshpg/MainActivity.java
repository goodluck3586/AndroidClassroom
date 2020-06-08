package com.example.intentshpg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mainEditText;
    Button goToSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainEditText = findViewById(R.id.mainEditText);
        goToSecondActivity = findViewById(R.id.goToSecondActivity);

        goToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainEditText.getText().toString().equals(""))
                    return;
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("mainData", mainEditText.getText().toString());
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            int flag = data.getIntExtra("secondData",-1);
            if(flag == 1) {
                // 암시적 인텐트 1 코드가 들어가는 부분
                Uri uri = Uri.parse("tel:0629496881");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            } else if(flag == 2) {
                // 암시적 인텐트 2 코드가 들어가는 부분
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                startActivity(intent);
            }
        }
    }
}
