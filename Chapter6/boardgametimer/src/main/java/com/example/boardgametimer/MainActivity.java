package com.example.boardgametimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //region 변수 선언
    EditText editTextEnteredSeconds;
    Button btnTimerSwitch, btnReset, btnPauseRestart;
    TextToSpeech textToSpeech;
    CountDownTimer countDownTimer;
    TextView textViewCopyright;

    long fullTime = 40;
    long halfTime = fullTime/2;
    boolean isPauseState = true;
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

        //region TextToSpeech 객체 생성
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);  //Select Language
                }
            }
        });
        //endregion

        //region btnTimerSwitch 클릭
        btnTimerSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MediaPlayer.create(getApplicationContext(), R.raw.bell_sound).setVolume(0.1f, 0.1f);
                MediaPlayer.create(getApplicationContext(), R.raw.bell_sound2).start();
                resetTimer();
                CancelCountDownTimer();
                countDownTimer = countDownTimer(fullTime);
                countDownTimer.start();
            }
        });
        //endregion

        //region RESET 버튼 롱클릭
        btnReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // 키보드와 editText의 Focus 없애기
                if(editTextEnteredSeconds.hasFocus()){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    editTextEnteredSeconds.clearFocus();
                }

                resetTimer();
                CancelCountDownTimer();

                String msg = String.format("Set to %d seconds.", fullTime);
                textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null);

                btnTimerSwitch.setEnabled(true);
                return false;
            }
        });
        //endregion

        //region PAUSE/RESTART 버튼 클릭
        btnPauseRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPauseState){
                    textToSpeech.speak("PAUSE", TextToSpeech.QUEUE_FLUSH, null);
                    btnPauseRestart.setText("RESTART");
                    countDownTimer.cancel();
                    isPauseState = false;
                }else{
                    textToSpeech.speak("RESTART", TextToSpeech.QUEUE_FLUSH, null);
                    btnPauseRestart.setText("PAUSE");
                    countDownTimer = countDownTimer(Long.parseLong(btnTimerSwitch.getText().toString()));
                    countDownTimer.start();
                    isPauseState = true;
                }
            }
        });
        //endregion
    }

     // 타이머 리셋
     private void resetTimer(){
        if(editTextEnteredSeconds.getText().equals("")){
            Toast.makeText(this, "입력된 시간이 없습니다!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        textViewCopyright.setVisibility(View.GONE);
        fullTime = Long.parseLong(editTextEnteredSeconds.getText().toString());
        btnPauseRestart.setText("PAUSE");
        isPauseState = true;
        ChangeTextSize(fullTime);  // TextSize 조절
        btnTimerSwitch.setText(String.valueOf(fullTime));
        btnTimerSwitch.setBackgroundColor(Color.YELLOW);
        btnTimerSwitch.setTextColor(Color.BLACK);
        halfTime = Math.round(fullTime/2);
    }

    // CountDownTimer 생성 및 반환
    private CountDownTimer countDownTimer(long t){
        return new CountDownTimer(t*1000+1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long currentTime = millisUntilFinished/1000;
                ChangeTextSize(currentTime);  // TextSize 조절
                if(currentTime == halfTime){
                    MediaPlayer.create(getApplicationContext(), R.raw.warning_sound).start();
                }
                if(currentTime <= 10){
                    btnTimerSwitch.setTextColor(Color.RED);
                    textToSpeech.speak(String.valueOf(currentTime), TextToSpeech.QUEUE_FLUSH, null);  //Text Convert to speech
                }
                btnTimerSwitch.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                MediaPlayer.create(getApplicationContext(), R.raw.gameover_sound).start();
                btnTimerSwitch.setTextColor(Color.DKGRAY);
                btnTimerSwitch.setBackgroundColor(Color.GRAY);
                btnTimerSwitch.setEnabled(false);
                countDownTimer.cancel();
                textViewCopyright.setVisibility(View.VISIBLE);
            }
        };
    }

    // btnTimerSwitch 텍스트 사이즈 조정
    private void ChangeTextSize(long seconds){
        if(seconds>=100){
            btnTimerSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 200);
        }else if(seconds>=10){
            btnTimerSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 300);
        }else{
            btnTimerSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 400);
        }
    }

    // countdownTimer cancel
    private void CancelCountDownTimer(){
        if(countDownTimer != null)
            countDownTimer.cancel();
    }

}


