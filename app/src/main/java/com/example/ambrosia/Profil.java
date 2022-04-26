package com.example.ambrosia;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ambrosia.Users.User;
import com.example.ambrosia.parametres.Parametres;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.rpc.PreconditionFailure;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Profil extends Fragment {

    private String Tag ="Bonjour";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ImageButton button = (ImageButton) view.findViewById(R.id.ntm);
        Button buttonCreation = (Button) view.findViewById(R.id.creation);
        Button buttonRecuperation = (Button) view.findViewById(R.id.recuperation);
        Button buttonConnection = (Button) view.findViewById(R.id.connection);

        TextView mail = (TextView) view.findViewById(R.id.mail);
        TextView age = (TextView) view.findViewById(R.id.age);
        TextView sexe = (TextView) view.findViewById(R.id.sexe);
        TextView allergie = (TextView) view.findViewById(R.id.allergie);
        TextView poids = (TextView) view.findViewById(R.id.poids);
        TextView nom = (TextView) view.findViewById(R.id.nom);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("clekceldekcel");
                Intent param = new Intent(getActivity(), Parametres.class);
                startActivity(param);
            }});


        buttonCreation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Map<String, Object> usere = new HashMap<>();
                usere.put("first", "valentin");
                usere.put("last", "nasone");
                usere.put("born", 2001);
                usere.put("password", "bonjour");
                usere.put("mail","valenti.nasone@gmail.com");
                usere.put("sexe", "masculin");

                db.collection("user").document("valentin").set(usere);
            }});


        buttonConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference docRef = db.collection("user").document("Valentin");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                if(document.getData().get("password").toString().equals("bonjour")){
                                    Log.d("Bonjour", "DocumentSnapshot data: " + document.getData());
                                    User user = document.toObject(User.class);
                                    nom.setText("Nom : " + user.getFirst());
                                    mail.setText("Mail : " + user.getMail());
                                    age.setText("Age : " + user.getBorn());
                                    sexe.setText("Sexe : " + user.getSexe());
                                }else {
                                    // nom.setText("Veuillez rentrer le bon mot de passe");

                                }
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



        return view;
    }

}