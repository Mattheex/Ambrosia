package com.example.ambrosia.planning.Details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.DayEnum;

import java.util.List;
import java.util.Map;

public class IngredientsAdapter extends BaseAdapter {
    private List<String> ingredients;
    private LayoutInflater inflater;
    public IngredientsAdapter(Context context, List<String> ingredients) {
        this.ingredients = ingredients;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int i) {
        return ingredients.get(i);
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
        ingredient.setText(ingredients.get(i));
        return layout;
    }
}
