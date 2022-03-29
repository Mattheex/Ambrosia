package com.example.ambrosia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.ambrosia.planning.Planning;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView binding;
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new Planning());

        binding = findViewById(R.id.bottomMenu);
        binding.getMenu().getItem(1).setChecked(true);
        binding.setOnNavigationItemSelectedListener(item -> {
            Log.d("switch", String.valueOf(item.getItemId()));
            switch (item.getItemId()) {
                case R.id.profil:
                    replaceFragment(new Profil());
                    break;
                case R.id.forum:
                    replaceFragment(new Forum());
                    break;
                case R.id.planning:
                    replaceFragment(new Planning());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}