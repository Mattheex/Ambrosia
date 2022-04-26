package com.example.ambrosia.parametres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ambrosia.R;

import java.util.Calendar;

public class ParametreProfil extends Fragment {
    public Calendar calendar;
    private DatePicker datePicker;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profile_name, container, false);
        ImageView button = (ImageView) myView.findViewById(R.id.imageAnniv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        TextView text= (TextView) myView.findViewById(R.id.anniversaire);
                        text.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        dateSetListener, 2000, 00, 1);
                datePickerDialog.show();

            }
        });

        return myView;
    }
}