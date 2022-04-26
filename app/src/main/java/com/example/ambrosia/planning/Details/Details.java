package com.example.ambrosia.planning.Details;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Food;

public class Details extends Activity {
    private Food food;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        food = getIntent().getParcelableExtra("food");

        Log.d("Parcelable", food.getName());
    }
}