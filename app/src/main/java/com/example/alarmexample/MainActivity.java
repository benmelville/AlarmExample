package com.example.alarmexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView edtTextHour;
    private TextView edtTextMinute;
    private Calendar calendar = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            edtTextMinute.setText(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        edtTextHour = findViewById(R.id.edtTextHour);
        edtTextMinute = findViewById(R.id.edtTextMinute);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SET_ALARM) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SET_ALARM}, 101);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
    }
        private void setAlarm() {

            int hours = Integer.valueOf(edtTextHour.getText().toString());
            int minutes = Integer.valueOf(edtTextMinute.getText().toString());

            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            intent.putExtra(AlarmClock.EXTRA_HOUR, hours);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "This is a test alarm");
            ArrayList<Integer> days = new ArrayList<>();
            days.add(Calendar.FRIDAY);
            days.add(Calendar.SUNDAY);
            days.add(Calendar.TUESDAY);
            intent.putExtra(AlarmClock.EXTRA_DAYS, days);

            startActivity(intent);

            Calendar calendar = Calendar.getInstance();
            int years = calendar.get(Calendar.DAY_OF_WEEK);
            Date date = calendar.getTime();
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            Toast.makeText(this, "Date: " + currentTime, Toast.LENGTH_SHORT).show();

//            DatePickerDialog datePickerDialog = new DatePickerDialog(
//                    this,
//                    listener,
//                    calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH)
//            );
//            datePickerDialog.show();

        }



}