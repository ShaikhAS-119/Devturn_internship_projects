package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int sec = 0;
    private boolean is_running=false;
    TextView hr;
    Button play,reset;

    Handler handler=new Handler();
    Runnable runnable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hr=findViewById(R.id.milisecond);

        play=findViewById(R.id.start);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!is_running){
                    play.setText("Pause");
                    play.setBackgroundColor(Color.parseColor("#ffffff"));
                    play.setTextColor(Color.parseColor("#000000"));
                    startTimer();
                }
                else{
                    play.setText("Play");
                    play.setBackgroundColor(Color.parseColor("#05104E"));
                    play.setTextColor(Color.parseColor("#ffffff"));
                    stopTimer();
                }
            }
        });

        reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hr.setText("00:00:00");
            }
        });
    }
    private void startTimer() {
        is_running=true;
         runnable=new Runnable() {
            @Override
            public void run() {
                sec++;
                updateTimer();
                handler.postDelayed(this,1000);
            }
        };
         handler.postDelayed(runnable,1000);
    }

    private void updateTimer() {
        int hours = sec / 3600;
        int minutes = (sec % 3600) / 60;
        int secs = sec % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        hr.setText(time);
    }

    private void stopTimer() {
        is_running=false;
        handler.removeCallbacks(runnable);
    }

}