package com.example.ambrosia.parametres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ambrosia.R;
import com.example.ambrosia.Users.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class ParametreProfil extends Fragment {
    public Calendar calendar;
    private DatePicker datePicker;
    public User user = new User();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        View myView = inflater.inflate(R.layout.fragment_profile_name, container, false);
        ImageView button = (ImageView) myView.findViewById(R.id.imageAnniv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        TextView text= (TextView) myView.findViewById(R.id.anniversaire);
                        text.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        dateSetListener, 2000, 00, 1);
                datePickerDialog.show();

            }
        });

        EditText lasts = (EditText) myView.findViewById(R.id.lastName);
        EditText firsts = (EditText) myView.findViewById(R.id.firstName);
        // RadioGroup sexes = (RadioGroup) myView.findViewById(R.id.radioGroup);

        Button creationProfil = (Button) myView.findViewById(R.id.buttonValidation);
        EditText emails = (EditText) myView.findViewById(R.id.email);
        EditText mdps = (EditText) myView.findViewById(R.id.mot_de_passe);

        creationProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String last = lasts.getText().toString();
                String first = firsts.getText().toString();
                String email = emails.getText().toString();
                String mdp = mdps.getText().toString();

                user.setMail(email);
                user.setFirst(first);
                user.setLast(last);
                user.setPassword(mdp);

                db.collection("user").document(user.getMail()).set(user);


            }
        });

        return myView;
    }
}