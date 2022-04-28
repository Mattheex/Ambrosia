package com.example.ambrosia.parametres;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.MainActivity;
import com.example.ambrosia.R;
import com.example.ambrosia.Users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ParametreProfilBis extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public User user;
    AlertDialog.Builder builder;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profile_mail, container, false);

        EditText emails = (EditText) myView.findViewById(R.id.emailConnexion);
        EditText mdps = (EditText) myView.findViewById(R.id.mdpConnexion);



        Button connexion = (Button) myView.findViewById(R.id.buttonConnexion);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emails.getText().toString();
                String mdp = mdps.getText().toString();

                if (email.length()<=1 || mdp.length()<=1) alerte("Veuillez remplir les différents champs");
                else {


                    DocumentReference docRef = db.collection("user").document(email);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    if (document.getData().get("password").toString().equals(mdp)) {
                                        Log.d("Bonjour", "DocumentSnapshot data: " + document.getData());
                                        recupererProfil(email);
                                        // nom.setText("nom : " + document.getData().get("first").toString());
                                    } else {
                                        alerte("Le mot de passe est incorrect");
                                        Log.d("Mot de passe", "Le mot de passe est incorrect");
                                        // nom.setText("Veuillez rentrer le bon mot de passe");

                                    }
                                } else {
                                    alerte("Le mail est incorrect");
                                    Log.d("Connexion probleme mail", "No such document");
                                }
                            } else {
                                Log.d("bonjour", "get failed with ", task.getException());
                            }
                        }
                    });

                    // DocumentReference docRef = db.

                }
            }
        });

        return myView;
    }

    public void recupererProfil(String emailss){
        DocumentReference docRef = db.collection("user").document(emailss);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                Intent home = new Intent(getActivity(), MainActivity.class);
                home.putExtra("Profil", (Parcelable) user);
                startActivity(home);
                getActivity().finish();
            }
        });
    }

    public void alerte(String msg){
        builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("ERREUR LORS DE LA CONNEXION");
        alert.show();
    }
}
