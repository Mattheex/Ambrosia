package com.example.ambrosia.parametres;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ParametreProfilTer extends Fragment {
    public Calendar calendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profile_regime, container, false);
        calendar= Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        ImageView button = (ImageView) myView.findViewById(R.id.finReg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        setTime(year,monthOfYear,dayOfMonth);
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
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        dateSetListener, year, month, day);
                datePickerDialog.show();

            }
        });
        return myView;
    }
    public void setTime(int year, int month, int dayOfMonth){
        calendar = new GregorianCalendar(year, month, dayOfMonth);
    }
}
