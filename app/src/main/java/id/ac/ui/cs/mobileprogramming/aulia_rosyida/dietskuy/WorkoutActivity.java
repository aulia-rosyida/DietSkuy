package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.String;
import android.util.Log;

public class WorkoutActivity extends AppCompatActivity {
    private TextView countdownText, reminderText;
    private Button countdownButton, resetButton, pickerButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 600000; //10 menit
    private boolean timerRunning;

    int reminderHour, reminderMinute;

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

        updateTime();

        resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeLeftInMilliSeconds = 600000;
                stopTimer();
                updateTime();
            }
        });

        reminderText = findViewById(R.id.reminder_text);
        pickerButton = findViewById(R.id.reminder_button);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisai timepicker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        WorkoutActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                //inisialisasi jam dan menit
                                reminderHour = hourOfDay;
                                reminderMinute = minute;

                                //inisialisasi calendar
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0, reminderHour, reminderMinute);

                                //set selected time on text view
                                DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
                                String timeString = dateFormat.format(calendar.getTime());
                                reminderText.setText(timeString);
                                Log.d("TAG", "masukkkkkk ke on timeset");
                                Log.d("TAG", timeString);
                                System.out.println(timeString);

                            }
                        }, 12, 0, false
                );
                //menampilkan pilihan waktu sebelumnya
                timePickerDialog.updateTime(reminderHour, reminderMinute);
                timePickerDialog.show();
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

        timeLeftText = "" + minutes;
        if(minutes < 10) timeLeftText="0" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText+="0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }
}