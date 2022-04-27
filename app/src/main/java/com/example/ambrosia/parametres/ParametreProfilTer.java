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



        return myView;
    }

}
