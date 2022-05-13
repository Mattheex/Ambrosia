package com.example.ambrosia.planning.Details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ambrosia.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NutrientsAdapter extends BaseAdapter {
    private List<String> nutrients = new ArrayList<>();
    private LayoutInflater inflater;

    public NutrientsAdapter(Context context, List<String> nutrients) {
        this.nutrients = nutrients;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return nutrients.size();
    }

    @Override
    public Object getItem(int i) {
        return nutrients.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout layout;

        layout = (RelativeLayout) (view == null ? inflater.inflate(R.layout.ingredientslist, viewGroup, false) : view);

        TextView ingredient = layout.findViewById(R.id.ingredient);
        ingredient.setText(nutrients.get(i));

        return layout;
    }
}
