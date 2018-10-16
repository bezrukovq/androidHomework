package com.example.vladimir.notfication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CountDownTimer cTimer = null;
    TextView tvTime;
    Button btnStart;
    Button btnStop;
    EditText seconds;
    EditText minutes;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = findViewById(R.id.tv_time);
        btnStop = findViewById(R.id.btn_stop);
        btnStop.setEnabled(false);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
            }
        });
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!minutes.getText().toString().equals("") && !seconds.getText().toString().equals("")) {
                    start();
                }
            }
        });
        minutes = findViewById(R.id.et_minutes);
        seconds = findViewById(R.id.et_seconds);
    }

    public void start() {
        long time = Integer.parseInt(minutes.getText().toString()) * 60000 + Integer.parseInt(seconds.getText().toString()) * 1000;
        startTimer(time);
        Toast.makeText(getApplicationContext(), "started", Toast.LENGTH_SHORT);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReciever.class);
        pendingIntent = PendingIntent.getBroadcast(this, 123, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pendingIntent);

    }

    void startTimer(long time) {
        cTimer = new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTime.setText(millisUntilFinished / 60000 + ":" + (millisUntilFinished % 60000) / 1000);
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "wake up", Toast.LENGTH_SHORT).show();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                tvTime.setText("set new time");
            }
        };
        cTimer.start();
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    }

    void cancelTimer() {
        alarmManager.cancel(pendingIntent);
        cTimer.cancel();
        tvTime.setText("set new time");
    }
}
