package com.example.ambrosia.planning;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ambrosia.R;

import java.util.ArrayList;
import java.util.List;


public class Days extends Fragment {

    DayAdapter dayAdapter;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_days, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<DayItems> dayItemsList = new ArrayList<>();
        DayItems dayItems = new DayItems();

        dayItems.setDej("Nutella, pain de mie, pomme");
        dayItems.setMidi("Poisson pané, lentilles");
        dayItems.setGouter("clémentine, thé");
        dayItems.setDiner("salade, tomate, mais");
        dayItemsList.add(dayItems);

        dayItems = new DayItems();
        dayItems.setDej("pom'pote");
        dayItems.setMidi("burger sale");
        dayItems.setGouter("tartine confiture fraise");
        dayItems.setDiner("ptit salade");
        dayItemsList.add(dayItems);

        dayItems = new DayItems();
        dayItems.setDej("oeufs");
        dayItems.setMidi("wraps thon");
        dayItems.setGouter("verre de lait");
        dayItems.setDiner("tomate cerise");
        dayItemsList.add(dayItems);

        dayAdapter = new DayAdapter(dayItemsList);

        linearLayout = view.findViewById(R.id.linearLayoutDays);

        ViewPager2 viewPager2 = view.findViewById(R.id.pager2);
        viewPager2.setAdapter(dayAdapter);

        setupDaysIndicator();
        setCurrentDay(0);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentDay(position);
            }
        });
    }

    private void setupDaysIndicator() {
        TextView[] days = new TextView[dayAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 0, 10, 0);
        String[] daysName = {"Lundi", "Mardi", "Mercredi", "Jeudi"};
        for (int i = 0; i < days.length; i++) {
            days[i] = new TextView(getContext());
            days[i].setText(daysName[i]);
            days[i].setLayoutParams(layoutParams);
            linearLayout.addView(days[i]);
        }
    }

    private void setCurrentDay(int index) {
       int childCount = linearLayout.getChildCount();
       for(int i = 0;i<childCount;i++){
           TextView textView = (TextView) linearLayout.getChildAt(i);
           if(i == index){
               textView.setTextColor(Color.parseColor("#000000"));
           } else {
               textView.setTextColor(Color.parseColor("#616161"));
           }
       }
    }
}