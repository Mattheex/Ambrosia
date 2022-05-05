package com.example.ambrosia;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

//import com.example.ambrosia.planning.Planning;
import com.example.ambrosia.Users.User;
import com.example.ambrosia.broadcast_receivers.NotificationEventReceiver;
import com.example.ambrosia.parametres.Parametres;
import com.example.ambrosia.planning.Planning;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView binding;
    public static User user;
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;
    Bundle bundle = new Bundle();
    public static int number = (int) (Math.random()*(10-1));
    Handler handler = new Handler();
    int delay = 1000;
    Runnable runnable;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Planning.newMotivatiion();

        user = getIntent().getParcelableExtra("Profil");

        Log.d("mise a jour profil", user.getFirst());
        setContentView(R.layout.activity_main);
        bundle.putParcelable("Profil",(Parcelable) user);

        Fragment Forum = new Forum();
        Fragment Planning = new Planning();
        Fragment Profil = new Profil();

        replaceFragment(Planning);



        binding = findViewById(R.id.parametreMenu);
        binding.getMenu().getItem(1).setChecked(true);
        binding.setOnNavigationItemSelectedListener(item -> {
            Log.d("switch", String.valueOf(item.getItemId()));
            switch (item.getItemId()) {
                case R.id.nextPage:
                    replaceFragment(Profil);
                    break;
                case R.id.previous:
                    replaceFragment(Forum);
                    break;
                case R.id.planning:
                    replaceFragment(Planning);
                    break;
            }
            return true;
        });
        sendNotification();

    }

    private void replaceFragment(Fragment fragment) {
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

    // au lancement de l'appli
    private void sendNotification() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(getClass().getSimpleName(),"NOTIFICATION");
            NotificationEventReceiver.setupAlarm(getApplicationContext());
        }).start();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    // To prevent crash on resuming activity  : interaction with fragments allowed only after Fragments Resumed or in OnCreate
    // http://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        // handleIntent();
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                Planning.newMotivatiion();
                MainActivity.number += 1;
                //Toast.makeText(getContext(), "This method is run every 10 seconds",
                //Toast.LENGTH_SHORT).show();
            }
        }, delay);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
    }

}