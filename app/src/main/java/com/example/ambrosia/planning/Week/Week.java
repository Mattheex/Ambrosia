package com.example.ambrosia.planning.Week;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ambrosia.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Week extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week, container, false);
    }
}