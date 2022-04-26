package com.example.ambrosia.broadcast_receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.legacy.content.WakefulBroadcastReceiver;

import com.example.ambrosia.R;
import com.example.ambrosia.notification.NotificationIntentService;

import java.util.Calendar;
import java.util.Date;

public class NotificationEventReceiver extends WakefulBroadcastReceiver {

    private static final String ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE";

    public static void setupAlarm(Context context) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = getStartPendingIntent(context);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                getTriggerAt(),
                alarmIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Intent serviceIntent = null;
        if (ACTION_START_NOTIFICATION_SERVICE.equals(action)) {
            Log.i(getClass().getSimpleName(), "onReceive from alarm, starting notification service");
            serviceIntent = NotificationIntentService.createIntentStartNotificationService(context);
        }
        if (serviceIntent != null) {
            startWakefulService(context, serviceIntent);
        }
    }

    private static long getTriggerAt() {
        //set de l'heure de la notification
        Calendar now = Calendar.getInstance();
        System.out.println(now.getTime());
        int jour = now.get(Calendar.DAY_OF_MONTH);
        int heure = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        System.out.println(jour);
        if(heure == 20 && minute <= 5) {
            now.set(Calendar.HOUR_OF_DAY, 20);
            now.set(Calendar.MINUTE, minute + 1);
        }
        else if(heure > 20){
            now.set(Calendar.DAY_OF_MONTH, jour+1);
            now.set(Calendar.HOUR_OF_DAY, 20);
            now.set(Calendar.MINUTE, 0);
        }
        else if(heure < 7){
            now.set(Calendar.HOUR_OF_DAY, 7);
            now.set(Calendar.MINUTE, 0);
        }
        else if(heure == 7 && minute <= 5){
            now.set(Calendar.HOUR_OF_DAY, 7);
            now.set(Calendar.MINUTE, minute+1);
        }
        else if(heure < 13){
            now.set(Calendar.HOUR_OF_DAY, 13);
            now.set(Calendar.MINUTE, 0);
        }
        else if(heure == 13 && minute <= 5){
            now.set(Calendar.HOUR_OF_DAY, 13);
            now.set(Calendar.MINUTE, minute+1);
        }
        else{
            now.set(Calendar.HOUR_OF_DAY, 20);
            now.set(Calendar.MINUTE, 0);
        }
        System.out.println(now.get(Calendar.DAY_OF_MONTH));
        System.out.println(now.get(Calendar.HOUR_OF_DAY));
        System.out.println(now.get(Calendar.MINUTE));
        return now.getTimeInMillis();
    }

    private static PendingIntent getStartPendingIntent(Context context) {
        Intent intent = new Intent(context, NotificationEventReceiver.class);
        intent.setAction(ACTION_START_NOTIFICATION_SERVICE);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
