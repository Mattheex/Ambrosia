package com.example.ambrosia.planning.Day;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Planning;

public class MealsAdapter extends BaseAdapter {
    private DayItems dayItem;
    private LayoutInflater inflater;
    private String[] mealType = new String[]{"Petit-déjeuner", "Déjeuner", "Goûter", "Diner"};

    public MealsAdapter(Context context, DayItems dayItem) {
        this.dayItem = dayItem;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dayItem.getSize();
    }

    @Override
    public Object getItem(int i) {
        return dayItem.getFood(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout layout;

        layout = (RelativeLayout) (view == null ? inflater.inflate(R.layout.daylist, viewGroup, false) : view);

        TextView type = layout.findViewById(R.id.mealType);
        TextView details = layout.findViewById(R.id.mealDetails);
        TextView cal = layout.findViewById(R.id.mealCal);

        type.setText(mealType[i]);
        details.setText(dayItem.getFood(i).getName());
        cal.setText(dayItem.getFood(i).getCal() + " cal");

        layout.setOnClickListener(click -> dayItem.click(i));
        return layout;
    }
}
