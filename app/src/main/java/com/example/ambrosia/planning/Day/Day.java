package com.example.ambrosia.planning.Day;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.example.ambrosia.planning.Details.Details;
import com.example.ambrosia.planning.Food;

/**
 * A simple {@link Fragment} subclass.
 */
public class Day extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.daysLayout).setOnClickListener(view3 -> {
            TextView textView = view3.findViewById(R.id.descriptionDej);
            Log.e("click", (String) textView.getText());
            //Toast.makeText(getContext(),textView.getText(),Toast.LENGTH_LONG).show();
            //view3.findViewWithTag(R.id.);
        });
    }
}