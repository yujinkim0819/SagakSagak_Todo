package com.cookandroid.sagaksagaktodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TimerFragment extends Fragment {
    public static TimerFragment newInstance(){
        return new TimerFragment();
    }
    private TextView countdownText; // 타이머 현황

    private Button startButton; // 시작 버튼
    private Button stopButton; // 정지 버튼
    private Button cancelButton; // 취소 버튼

    private EditText hourText; // 시
    private EditText minText; // 분
    private EditText secondText; // 초

    private CountDownTimer countDownTimer;

    private boolean timerRunning; // 타이머 상태
    private boolean firstStart; // 처음인지 아닌지?

    private long time = 0;
    private long tempTime = 0;

    FrameLayout setting; // 셋팅화면
    FrameLayout timer; // 타이머 화면


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup containers, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_timer, containers, false);

        countdownText = view.findViewById(R.id.countdown_text);
        startButton = view.findViewById(R.id.countdown_button); // 시작
        stopButton = view.findViewById(R.id.stop_btn); // 정지
        cancelButton = view.findViewById(R.id.cancel_btn); // 취소

        hourText = view.findViewById(R.id.hour); // 시
        minText = view.findViewById(R.id.min); // 분
        secondText = view.findViewById(R.id.second); // 초

        setting = view.findViewById(R.id.setting);
        timer = view.findViewById(R.id.timer);

        // 타이머 시작
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstStart = true;
                setting.setVisibility(setting.GONE); // 설정 사라짐
                timer.setVisibility(timer.VISIBLE); // 타이머 생김
                startStop();
            }
        });

        // 일시정지
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });

        // 취소
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setting.setVisibility(setting.VISIBLE); // 설정 생김
                timer.setVisibility(timer.GONE); // 타이머 사라짐
                firstStart = true;
                stopTimer();
            }
        });

        updateTimer();

        return view;
    }

    // 타이머 상태에 따른 시작과 정지
    private void startStop(){
        if(timerRunning){ // 시작이면 정지
            stopTimer();
        } else {
            startTimer(); // 정지면 시작
        }
    }

    // 타이머 구현
    private void startTimer(){
        if(firstStart){
            String sHour = hourText.getText().toString();
            String sMin = minText.getText().toString();
            String sSecond = secondText.getText().toString();
            time = (Long.parseLong(sHour) * 3600000) + (Long.parseLong(sMin) * 60000) + (Long.parseLong(sSecond) * 1000) + 1000;
        } else {
            time = tempTime;
        }
        countDownTimer = new CountDownTimer(time, 1000){
            @Override
            public void onTick(long millisUntilFinished){
                tempTime = millisUntilFinished;
                updateTimer();
            }
            @Override
            public void onFinish(){}
        }.start();
        stopButton.setText("일시정지");
        timerRunning = true;
        firstStart = false;
    }

    // 타이머 정지
    private void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
        stopButton.setText("계속");
    }

    // 시간 업데이트
    private void updateTimer(){
        int hour = (int) tempTime / 3600000;
        int minutes = (int)tempTime % 3600000 / 60000;
        int seconds = (int) tempTime % 3600000 % 60000 / 1000;

        String timerLeftText = "";
        timerLeftText = "" + hour + ":";

        if(minutes < 10) timerLeftText += "0";
        timerLeftText += minutes + ":";

        // 초가 10보다 작으면 0이 붙는다
        if(seconds < 10) timerLeftText += "0";
        timerLeftText += seconds;

        countdownText.setText(timerLeftText);
    }
}