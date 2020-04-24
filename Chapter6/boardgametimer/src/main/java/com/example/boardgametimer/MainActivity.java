package com.example.boardgametimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //region 변수 선언
    // 1. 위젯 참조 변수 선언
    EditText editTextEnteredSeconds;
    Button btnTimerSwitch, btnReset, btnPauseRestart;
    TextToSpeech textToSpeech;
    CountDownTimer countDownTimer;
    TextView textViewCopyright;

    // 2. 카운트다운 시간 변수 선언
    long fullTime = 12;
    long halfTime = fullTime/2;
    boolean isPause = true;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region 참조 변수에 UI 객체 연결
        editTextEnteredSeconds = findViewById(R.id.editTextEnteredSeconds);
        btnTimerSwitch = findViewById(R.id.btnTimerSwitch);
        btnReset = findViewById(R.id.btnReset);
        btnPauseRestart = findViewById(R.id.btnPauseRestart);
        textViewCopyright = findViewById(R.id.textViewCopyright);
        //endregion

        createObjectTextToSpeech();  //TextToSpeech 객체 생성

        //region btnTimerSwitch 클릭
        btnTimerSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer.create(getApplicationContext(), R.raw.bell_sound2).start(); // 벨소리
                cancelCountDownTimer();                                                 // 실행되던 CountDownTimer 멈추기
                resetTimer();                                                           // CountDownTimer 초기화
                countDownTimer = countDownTimer(fullTime);                              // 새로운 CountDownTimer 생성
                countDownTimer.start();                                                 // CountDownTimer 동작 시작
            }
        });
        //endregion

        //region RESET 버튼 클릭
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "RESET 버튼은 롱~클릭으로 동작합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion

        //region RESET 버튼 롱클릭
        btnReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 타이머 멈추고, 초기화
                cancelCountDownTimer();
                resetTimer();

                // 타이머 리셋 알림
                String msg = String.format("Reset to %d seconds.", fullTime);
                textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null);

                // 키보드와 editText의 Focus 없애기
                if(editTextEnteredSeconds.hasFocus()){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextEnteredSeconds.getWindowToken(), 0);
                    editTextEnteredSeconds.clearFocus();
                }

                // btnTimerSwitch 버튼 활성화
                btnTimerSwitch.setEnabled(true);
                return true;
            }
        });
        //endregion

        //region PAUSE/RESTART 버튼 클릭
        btnPauseRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPause){
                    textToSpeech.speak("PAUSE", TextToSpeech.QUEUE_FLUSH, null);
                    btnPauseRestart.setText(R.string.restart);
                    countDownTimer.cancel();
                    isPause = false;
                }else{
                    textToSpeech.speak("RESTART", TextToSpeech.QUEUE_FLUSH, null);
                    btnPauseRestart.setText(R.string.pause);
                    countDownTimer = countDownTimer(Long.parseLong(btnTimerSwitch.getText().toString()));
                    countDownTimer.start();
                    isPause = true;
                }
            }
        });
        //endregion
    }

     // 타이머 리셋
     private void resetTimer(){
        // 입력된 시간(초) 체크
        if(editTextEnteredSeconds.getText().equals("")){
            Toast.makeText(this, "입력된 시간이 없습니다!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        // editText에 입력된 시간(초)로 변수 초기화
        fullTime = Long.parseLong(editTextEnteredSeconds.getText().toString());
        halfTime = Math.round(fullTime/2);

        // btnTimerSwitch 모양 초기화
        btnTimerSwitch.setText(String.valueOf(fullTime));
        btnTimerSwitch.setBackgroundColor(Color.YELLOW);
        btnTimerSwitch.setTextColor(Color.BLACK);
        changeTextSize(fullTime);                           // 새로 입력된 시간(초)에 따라 TextSize 조절

        // PAUSE/RESTART 버튼을 PAUSE 상태로 초기화
        btnPauseRestart.setText(R.string.pause);
        isPause = true;

        textViewCopyright.setVisibility(View.GONE);     // 저작자 표시 숨기기
    }

    // CountDownTimer 생성 및 반환
    private CountDownTimer countDownTimer(long t){
        return new CountDownTimer(t*1000+1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long currentTime = millisUntilFinished/1000;
                changeTextSize(currentTime);  // TextSize 조절

                // 시간이 절반 남았으면 경고음 재생
                if(currentTime == halfTime){
                    MediaPlayer.create(getApplicationContext(), R.raw.warning_sound).start();
                }

                // 시간이 10초 이하로 남으면 Countdown 재생
                if(currentTime <= 10){
                    btnTimerSwitch.setTextColor(Color.RED);
                    textToSpeech.speak(String.valueOf(currentTime), TextToSpeech.QUEUE_FLUSH, null);  //Text Convert to speech
                }

                // 시간(초) 표시
                btnTimerSwitch.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();    // 타이머 멈추기
                MediaPlayer.create(getApplicationContext(), R.raw.gameover_sound).start();  // Game Over 사운드 재생

                // btnTimerSwitch 버튼 모양 수정
                btnTimerSwitch.setTextColor(Color.GRAY);
                btnTimerSwitch.setBackgroundColor(Color.DKGRAY);

                // btnTimerSwitch 버튼 비활성화
                btnTimerSwitch.setEnabled(false);

                // 저작자 문구 표시
                textViewCopyright.setVisibility(View.VISIBLE);
            }
        };
    }

    // countdownTimer cancel
    private void cancelCountDownTimer(){
        if(countDownTimer != null)
            countDownTimer.cancel();
    }

    // btnTimerSwitch 텍스트 사이즈 조정
    private void changeTextSize(long seconds){
        if(seconds>=100){
            btnTimerSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 200);
        }else if(seconds>=10){
            btnTimerSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 300);
        }else{
            btnTimerSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 400);
        }
    }

    // TextToSpeech 객체 생성
    private void createObjectTextToSpeech(){
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(Locale.ENGLISH);  //Select Language
            }
        });
    }
}


