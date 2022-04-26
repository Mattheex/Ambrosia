package com.example.ambrosia;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class Channel extends Application {
    public static final String CHANNEL_ID="channel11";

    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }

    private static NotificationManager notificationManager;
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel("channel ambrosia", "Channel pour l'application de démonstration des notifications", NotificationManager.IMPORTANCE_HIGH);
    }

    private void createNotificationChannel(String name, String description, int importanceDefault) {
        //for API 26+
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importanceDefault);
            channel.setDescription(description);
            //cannot be changed after
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }
}
