package com.example.ambrosia.planning.Details;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Food;

public class Details extends Fragment {
    private Food food;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //food = getIntent().getParcelableExtra("food");

        //TextView textView = findViewById(R.id.food);
        //textView.setText("oui");

        //Log.d("Parcelable", food.getName());
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

        TextView textView = view.findViewById(R.id.food);
        textView.setText(food.getName());
    }
}