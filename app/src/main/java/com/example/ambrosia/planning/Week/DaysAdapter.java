package com.example.ambrosia.planning.Week;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Day.DayEnum;
import com.example.ambrosia.planning.Planning;

public class DaysAdapter extends BaseAdapter {
    private WeekItems week;
    private LayoutInflater inflater;
    private Planning planning;
    private Integer weekPos;

    public DaysAdapter(Context context, WeekItems week, Integer weekPos,Planning planning) {
        this.week = week;
        this.weekPos = weekPos;
        this.inflater = LayoutInflater.from(context);
        this.planning = planning;
    }

    @Override
    public int getCount() {
        return week.getSize();
    }

    @Override
    public Object getItem(int i) {
        return week.getDay(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout layout;

        layout = (RelativeLayout) (view == null ? inflater.inflate(R.layout.weeklist, viewGroup, false) : view);

        TextView day = layout.findViewById(R.id.weekDay);
        TextView kcal = layout.findViewById(R.id.kcalDay);

        day.setText(DayEnum.get(i));
        kcal.setText(week.getDay(i).sumKcal() + " cal");

        layout.setOnClickListener(click ->
                planning.newDays(weekPos,i));
        return layout;
    }
}
