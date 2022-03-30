package com.example.ambrosia.planning;

import static com.example.ambrosia.planning.Day.DayEnum.Jeudi;
import static com.example.ambrosia.planning.Day.DayEnum.Lundi;
import static com.example.ambrosia.planning.Day.DayEnum.Mardi;
import static com.example.ambrosia.planning.Day.DayEnum.Mercredi;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Day.DayAdapter;
import com.example.ambrosia.planning.Day.DayItems;
import com.example.ambrosia.planning.Week.WeekAdapter;
import com.example.ambrosia.planning.Week.WeekItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Planning extends Fragment {
    boolean day = true;
    DayAdapter dayAdapter;
    WeekAdapter weekAdapter;
    LinearLayout linearLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<DayItems> dayItemsList = listDays();
        List<WeekItems> weekItemsList = listWeeks();
        dayAdapter = new DayAdapter(dayItemsList);
        weekAdapter = new WeekAdapter(weekItemsList);
        linearLayout = view.findViewById(R.id.linearLayoutDays);

        ViewPager2 viewPager2 = view.findViewById(R.id.pager2);

        viewPager2.setAdapter(dayAdapter);
        setupDaysIndicator();
        setCurrentDay(0);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (day) {
                    setCurrentDay(position);
                } else {
                    setCurrentWeek(position);
                }
            }
        });

        Button button = view.findViewById(R.id.buttonChange);
        button.setOnClickListener(view1 -> {
            if (day) {
                viewPager2.setAdapter(weekAdapter);
                setupWeeksIndicator();
                setCurrentWeek(0);
                day = false;
                button.setText("Days");
            } else {
                viewPager2.setAdapter(dayAdapter);
                setupDaysIndicator();
                setCurrentDay(0);
                day = true;
                button.setText("Weeks");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    private List<DayItems> listDays() {
        List<DayItems> dayItemsList = new ArrayList<>();

        DayItems dayItems = new DayItems(Lundi);
        dayItems.setDej("Nutella, pain de mie, pomme");
        dayItems.setMidi("Poisson pané, lentilles");
        dayItems.setGouter("clémentine, thé");
        dayItems.setDiner("salade, tomate, mais");
        dayItemsList.add(dayItems);

        dayItems = new DayItems(Mardi);
        dayItems.setDej("pom'pote");
        dayItems.setMidi("burger sale");
        dayItems.setGouter("tartine confiture fraise");
        dayItems.setDiner("ptit salade");
        dayItemsList.add(dayItems);

        dayItems = new DayItems(Mercredi);
        dayItems.setDej("oeufs");
        dayItems.setMidi("wraps thon");
        dayItems.setGouter("verre de lait");
        dayItems.setDiner("tomate cerise");
        dayItemsList.add(dayItems);

        dayItems = new DayItems(Jeudi);
        dayItems.setDej("Nutella, pain de mie, pomme");
        dayItems.setMidi("Poisson pané, lentilles");
        dayItems.setGouter("clémentine, thé");
        dayItems.setDiner("salade, tomate, mais");
        dayItemsList.add(dayItems);

        return dayItemsList;
    }

    private List<WeekItems> listWeeks() {
        List<WeekItems> weekItemsList = new ArrayList<>();
        WeekItems weekItems = new WeekItems(Arrays.asList(2, 3, 4, 5));
        weekItemsList.add(weekItems);
        weekItems = new WeekItems(Arrays.asList(2, 9, 4, 15));
        weekItemsList.add(weekItems);
        weekItems = new WeekItems(Arrays.asList(37, 40, 18, 5));
        weekItemsList.add(weekItems);
        weekItems = new WeekItems(Arrays.asList(2, 80, 160, 550));
        weekItemsList.add(weekItems);
        return weekItemsList;
    }

    private void setupDaysIndicator() {
        TextView[] days = new TextView[3];
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 0, 10, 0);
        for (int i = 0; i < 3; i++) {
            days[i] = new TextView(getContext());
            days[i].setText(dayAdapter.getDay(i).toString());
            days[i].setLayoutParams(layoutParams);
            days[i].setTextSize(20);
            linearLayout.addView(days[i]);
        }
    }

    private void setupWeeksIndicator() {
        TextView[] days = new TextView[3];
        linearLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 0, 10, 0);
        for (int i = 0; i < 3; i++) {
            days[i] = new TextView(getContext());
            days[i].setText("Week " + i);
            days[i].setLayoutParams(layoutParams);
            linearLayout.addView(days[i]);
        }
    }

    private void setCurrentDay(int index) {
        int min, max;
        if (index == 0) {
            min = index;
            max = index + 2;
        } else if (index == dayAdapter.getItemCount() - 1) {
            min = index - 2;
            max = index;
        } else {
            min = index - 1;
            max = index + 1;
        }
        for (int i = min; i <= max; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i - min);
            textView.setText(dayAdapter.getDay(i).toString());
            textView.setTextColor(Color.parseColor((i == index) ? "#000000" : "#616161"));
        }
    }

    private void setCurrentWeek(int index) {
        int min, max;
        if (index == 0) {
            min = index;
            max = index + 2;
        } else if (index == weekAdapter.getItemCount() - 1) {
            min = index - 2;
            max = index;
        } else {
            min = index - 1;
            max = index + 1;
        }
        for (int i = min; i <= max; i++) {
            TextView textView = (TextView) linearLayout.getChildAt(i - min);
            textView.setText("Week " + i);
            textView.setTextSize(20);
            textView.setTextColor(Color.parseColor((i == index) ? "#000000" : "#616161"));
        }
    }
}