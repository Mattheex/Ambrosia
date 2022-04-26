package com.example.ambrosia;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

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
        createNotificationChannel();

        replaceFragment(new Planning());

        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, NotificationMessage.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long timeAtButtonClick = System.currentTimeMillis();

            long tenSecondsInMillis = 1000*10;

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    timeAtButtonClick + tenSecondsInMillis,
                    pendingIntent);


        });

        binding = findViewById(R.id.bottomMenu);
        binding.getMenu().getItem(1).setChecked(true);
        binding.setOnNavigationItemSelectedListener(item -> {
            Log.d("switch", String.valueOf(item.getItemId()));
            switch (item.getItemId()) {
                case R.id.nextPage:
                    replaceFragment(new Profil());
                    break;
                case R.id.previous:
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

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Repas";
            String description = "Rappel de manger !";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel Ambrosia", name, importance);
            channel.setDescription(description);

            //cannot be changed after
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}