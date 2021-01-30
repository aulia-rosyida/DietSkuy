package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.String;

public class WorkoutActivity extends AppCompatActivity {
    private TextView countdownText, reminderText;
    private Button countdownButton, resetButton, pickerButton, cancelButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 600000; //10 menit
    private boolean timerRunning;
    private OpenGLView openGLView, openGLView2;
    int reminderHour, reminderMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        openGLView = (OpenGLView) findViewById(R.id.openGLView);
        openGLView2 = (OpenGLView) findViewById(R.id.openGLView2);
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

                                updateTimeText(calendar);
                                startAlarm(calendar);
                            }
                        }, 12, 0, false
                );
                //menampilkan pilihan waktu sebelumnya
                timePickerDialog.updateTime(reminderHour, reminderMinute);
                timePickerDialog.show();
            }
        });

        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                cancelAlarm();
            }
       });
    }

    @Override
    protected void onResume() {
        super.onResume();
        openGLView.onResume();
        openGLView2.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        openGLView.onPause();
        openGLView2.onPause();
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        reminderText.setText("Alarm canceled");
    }

    private void updateTimeText(Calendar calendar) {
        //set selected time on text view
        DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        String timeString = "Alarm set for:";
        timeString += dateFormat.format(calendar.getTime());
        reminderText.setText(timeString);
    }

    private void startAlarm(Calendar c) {
        Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        long wakeTime = c.getTimeInMillis();
        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, wakeTime, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeTime, pendingIntent);
        } else
            alarmManager.set(AlarmManager.RTC_WAKEUP, wakeTime, pendingIntent);

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