package com.example.countdowntimer1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private TextView textViewTimer;
    Button btnStart, btnStop, btnPause;

    private long startMillisTime = 30 * 1000;
    private long MILLISINFUTURE = startMillisTime;
    private boolean isTimerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTimer = findViewById(R.id.textViewTime);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnPause = findViewById(R.id.btnPause);

        countDownTimer(30);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MILLISINFUTURE = startMillisTime;
                countDownTimer.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewTimer.setText(String.valueOf(MILLISINFUTURE/1000));
                countDownTimer.cancel();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
//                MILLISINFUTURE = 10 * 1000;
                countDownTimer(10);
                countDownTimer.start();

            }
        });
    }

    private void countDownTimer(long time){
        countDownTimer = new CountDownTimer(time*1000 + 1000, 1000) { //30초 동안 1초의 간격으로 onTick 메소드를 호출합니다.

            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(String.valueOf(millisUntilFinished / 1000));
            } //1초마다 호출되면서 남은 시간을 초 단위로 보여 줍니다. 30, 29, 28.. 이런식으로 나타나게 됩니다.

            public void onFinish() {
                textViewTimer.setText("done!");
            } //종료 되었을 때, "done!" 이라는 문자열을 보여줍니다.
        };
    }
}
