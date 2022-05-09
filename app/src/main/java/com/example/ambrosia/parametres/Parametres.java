package com.example.ambrosia.parametres;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.R;
import com.example.ambrosia.Users.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class Parametres extends AppCompatActivity {

    public User user;
    BottomNavigationView binding;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("parameter","before replace fragment");
        setContentView(R.layout.activity_profil);
        replaceFragment(new ParametreProfilTer());
        binding = findViewById(R.id.parametreMenu);
        binding.getMenu().getItem(1).setChecked(true);
        binding.setOnNavigationItemSelectedListener(item -> {
            Log.d("switch", String.valueOf(item.getItemId()));
            switch (item.getItemId()) {
                case R.id.connexion:
                    replaceFragment(new ParametreProfilBis());
                    break;
                case R.id.inscription:
                    replaceFragment(new ParametreProfil());
                    break;
                case R.id.home:
                    replaceFragment(new ParametreProfilTer());
                    break;
            }
            return true;
        });

        /*
        EditText email = (EditText) findViewById(R.id.email);
        EditText mdp = (EditText) findViewById(R.id.email);

        EditText last = (EditText) findViewById(R.id.lastName);
        EditText first = (EditText) findViewById(R.id.firstName);
        RadioGroup sexe = (RadioGroup) findViewById(R.id.radioGroup);

        User newUtilisateur = new User();

        Button validerPageUne = (Button) findViewById(R.id.buttonValidation);
        validerPageUne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUtilisateur.setLast(last.toString());
                newUtilisateur.setFirst(first.toString());
                newUtilisateur.setSexe(sexe.toString());
            }
        });

        // Button ValiderPageDeux = (Button) findViewById(R.id.buttonValidation1);


        Button creationProfil = (Button) findViewById(R.id.button3);
        creationProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUtilisateur.setMail(email.toString());
                newUtilisateur.setPassword(mdp.toString());

                db.collection("user").document(user.getFirst()).set(user);


            }
        });

*/

    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.inscription, fragment)
                .commit();
    }

}
