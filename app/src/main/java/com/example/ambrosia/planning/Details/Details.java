package com.example.ambrosia.planning.Details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Week.DaysAdapter;

public class Details extends Fragment {
    private Food food;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        food = bundle.getParcelable("food");

        TextView mealLabel = view.findViewById(R.id.mealLabel);
        TextView recipe = view.findViewById(R.id.recipe);

        mealLabel.setText(food.getName());

        IngredientsAdapter adapter = new IngredientsAdapter(view.getContext(), food.getIngredientList());
        ListView list = view.findViewById(R.id.recipeList);
        list.setAdapter(adapter);
    }
}