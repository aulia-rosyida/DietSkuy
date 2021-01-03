package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 600000; //10 menit
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        countdownText = findViewById(R.id.timer);
        countdownButton = findViewById(R.id.start_button);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
    }

    public void startStop() {
        if(timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }

    public void stopTimer() {
        countDownTimer.cancel();
        countdownButton.setText(R.string.start);
        timerRunning = false;
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliSeconds = l;
                updateTime();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        countdownButton.setText(R.string.pause);
        timerRunning = true;
    }

    public void updateTime() {
        int minutes = (int) timeLeftInMilliSeconds / 60000;
        int seconds = (int) timeLeftInMilliSeconds % 60000 / 1000;

        String timeLeftText;
        
        timeLeftText = "0" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText+="0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }
}