package com.example.ambrosia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ParametreProfil extends AppCompatActivity {
    public Calendar calendar;
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre_profil);
        datePicker= (DatePicker) this.findViewById(R.id.myCalendar);
        calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        this.datePicker.init( year, month , day , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                setTime( year,   month,   dayOfMonth);
            }
        });
    }

    public void onClick(View view){
        long time = calendar.getTime().getTime();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime",time);
        intent.putExtra("allDay", true);
        intent.putExtra("rule", "FREQ=YEARLY");
        intent.putExtra("endTime", time+60*60*1000);
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent);
    }

    public void setTime(int year, int month, int dayOfMonth){
        calendar = new GregorianCalendar(year, month, dayOfMonth);
    }
}