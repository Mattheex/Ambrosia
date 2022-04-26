package com.example.ambrosia.parametres;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.example.ambrosia.Users.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class ParametreProfilBis extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profile_mail, container, false);

        return myView;
    }
}
