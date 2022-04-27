package com.example.ambrosia.notification;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import com.example.ambrosia.Channel;
import com.example.ambrosia.MainActivity;
import com.example.ambrosia.R;

import java.util.Calendar;

public class NotificationIntentService extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_START = "ACTION_START";

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }

    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                processStartNotification();
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    public Bitmap getImage(){
        //set de l'image de la notification
        Bitmap bitmap;
        Calendar now = Calendar.getInstance();
        int heure = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        if(heure == 20 && minute <= 5) {
            bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.diner);
        }
        else if(heure > 20 || heure < 7 || (heure == 7 && minute <= 5)){
            bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.petitdej);
        }
        else if((heure < 13) || (heure == 13 && minute <= 5)){
            bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.dejeuner);
        }
        else if(heure <= 16){
            bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.gouter);
        }
        else{
            bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.diner);
        }
        return bitmap;
    }

    private void processStartNotification() {
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel.CHANNEL_ID)
                .setSmallIcon(R.drawable.ambrosia)
                .setContentTitle("Rappel repas")
                .setContentText("C'est l'heure de manger !")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(getImage()))
                .setTimeoutAfter(300000)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);

        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

}
