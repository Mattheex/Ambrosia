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
import java.text.CollationElementIterator;
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

        Button buttonRecuperation = (Button) view.findViewById(R.id.recuperation);
        Button buttonConnection = (Button) view.findViewById(R.id.connection);

        TextView pseudo = (TextView) view.findViewById(R.id.pseudo);
        TextView age = (TextView) view.findViewById(R.id.age);
        TextView sexe = (TextView) view.findViewById(R.id.sexe);
        TextView allergie = (TextView) view.findViewById(R.id.allergie);
        TextView poids = (TextView) view.findViewById(R.id.poids);
        TextView programme = (TextView) view.findViewById(R.id.programme);
        TextView nom = (TextView) view.findViewById(R.id.nom);

        AffichageProfil(nom, pseudo, age, sexe, poids, programme, allergie);


        buttonRecuperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.user.setFirst(" BALOU DESORMAIS");
                AffichageProfil(nom, pseudo, age, sexe, poids, programme, allergie);
                // La on doit changer le nom dans FIREBASE
            }
        });


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
                                    User userz = document.toObject(User.class);
                                    nom.setText("Nom : " + userz.getFirst());
                                    pseudo.setText("Pseudo : " + userz.getPseudo());
                                    age.setText("Age : " + userz.getAge());
                                    sexe.setText("Sexe : " + userz.getSexe());
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

    public void AffichageProfil(TextView nom, TextView pseudo, TextView age, TextView sexe, TextView poids, TextView programme, TextView allergie){
        nom.setText("Nom : " + MainActivity.user.getFirst());
        pseudo.setText("Pseudo : " + MainActivity.user.getPseudo());
        age.setText("Age : " + MainActivity.user.getAge());
        sexe.setText("Sexe : " + MainActivity.user.getSexe());
        poids.setText("Poids : " + MainActivity.user.getPoidsActuel() + " Kg");
        programme.setText("Programme : " + MainActivity.user.getProgramme());
        allergie.setText("Allergies : " + MainActivity.user.getAllergie());

    }

}