package com.example.ambrosia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Profil extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        ImageButton button = (ImageButton) view.findViewById(R.id.ntm);
        TextView nom = (TextView) view.findViewById(R.id.nom);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("clekceldekcel");
                nom.setText("ntm");
                Intent param = new Intent(getActivity(),ParametreProfil.class);
                startActivity(param);
            }});


        return view;
    }


}