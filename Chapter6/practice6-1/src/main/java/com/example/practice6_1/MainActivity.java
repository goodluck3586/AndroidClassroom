package com.example.practice6_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    //region 참조 변수 선언
    Chronometer chronometer;
    Button btnStart, btnEnd;
    RadioGroup radioGroup;
    RadioButton rdoCal, rdoTime;
    DatePicker datePicker;
    TimePicker timePicker;
    TextView textViewDate;
    int selectYear, selectMonth, selectDay, selectTime, selectMinute;
    //endregion

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region 참조 변수에 위젯 연결
        chronometer = findViewById(R.id.chronometer1);
        radioGroup = findViewById(R.id.radioGroup);
        rdoCal = findViewById(R.id.rdoCal);
        rdoTime = findViewById(R.id.rdoTime);
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        datePicker = findViewById(R.id.datePicker1);
        timePicker = findViewById(R.id.timePicker1);
        textViewDate = findViewById(R.id.tvYear);
        //endregion

        // calendarView와 timePicker 감추기
        datePicker.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);

        //region radioButton으로 라디오 버튼 선택 처리
        /*rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.INVISIBLE);
                calendarView.setVisibility(View.VISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });*/
        //endregion

        //region rddioGroup으로 라디오 버튼 선택 처리
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rdoCal:
                        timePicker.setVisibility(View.INVISIBLE);
                        datePicker.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rdoTime:
                        datePicker.setVisibility(View.INVISIBLE);
                        timePicker.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        //endregion

        chronometer.start();
        chronometer.setTextColor(Color.RED);

        //region <예약 시작> 버튼을 클릭하면 Chronometer 시작
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            }
        });
        //endregion

        //region <예약 완료> 버튼을 클릭하면 Chronometer 정지
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                chronometer.setTextColor(Color.BLUE);

                String reservedDate = String.format("%d년 %d월 %d일 %d시 %d분으로 예약됨", selectYear, selectMonth, selectDay, selectTime, selectMinute);

                textViewDate.setText(reservedDate);
            }
        });
        //endregion

        //region calendarView와 timePicker 선택 이벤트 처리
        /*calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month;
                selectDay = dayOfMonth;
            }
        });*/
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectYear = year;
                selectMonth = monthOfYear;
                selectDay = dayOfMonth;
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selectTime = hourOfDay;
                selectMinute = minute;
            }
        });
        //endregion
    }
}
