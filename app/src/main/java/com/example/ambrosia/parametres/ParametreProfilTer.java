package com.example.ambrosia.parametres;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.MainActivity;
import com.example.ambrosia.R;
import com.example.ambrosia.Users.User;
import com.example.ambrosia.Wait;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ParametreProfilTer extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public User user;

    public Calendar calendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("terCreate","terCreate");
        View myView = inflater.inflate(R.layout.fragment_profile_regime, container, false);

        Button entry = (Button) myView.findViewById(R.id.Entry);
        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DocumentReference docRef = db.collection("user").document("Christinedu06");
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()){
                                    Log.d("Bonjour", "DocumentSnapshot data: " + document.getData());
                                    user = document.toObject(User.class);
                                    Intent home = new Intent(getActivity(), Wait.class);
                                    home.putExtra("Profil", (Parcelable) user);
                                    startActivity(home);
                                    getActivity().finish();
                                }else{
                                    Log.d("bonjour", "No such document");
                                }
                            }else{
                                Log.d("bonjour", "get failed with ", task.getException());
                            }
                        }
                    });
            }
        });
        return myView;
    }

}
