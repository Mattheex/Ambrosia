package com.example.ambrosia.parametres;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.Forum;
import com.example.ambrosia.Profil;
import com.example.ambrosia.R;
import com.example.ambrosia.planning.Planning;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Parametres extends AppCompatActivity {
    BottomNavigationView binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        replaceFragment(new ParametreProfil());
        binding = findViewById(R.id.parametreMenu);
        binding.getMenu().getItem(0).setChecked(true);
        binding.setOnNavigationItemSelectedListener(item -> {
            Log.d("switch", String.valueOf(item.getItemId()));
            switch (item.getItemId()) {
                case R.id.infoSecu:
                    replaceFragment(new ParametreProfilBis());
                    break;
                case R.id.infoPerso:
                    replaceFragment(new ParametreProfil());
                    break;
                case R.id.infoRegime:
                    replaceFragment(new ParametreProfilTer());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.inscription, fragment)
                .commit();
    }
}
