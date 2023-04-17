package com.example.workoutfitnesstracker;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.Calendar;

public class DarkmodeChecker extends android.app.Application {
    SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences=getSharedPreferences("Userinfo",MODE_PRIVATE);

        if(preferences.getBoolean("DarkMode",false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        createNotificationChannel();

        //set the repeating notification
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE,12);
        calendar.set(Calendar.SECOND, 10);

        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);

        Intent MyIntent = new Intent(getApplicationContext(), NotificationBroadcastReciever.class);
        PendingIntent MyPendIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,
                MyIntent, 0 );

        //calendar.setTimeInMillis(System.currentTimeMillis());
        //calendar.add(Calendar.SECOND, 30);

        AlarmManager MyAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        MyAlarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, MyPendIntent);


    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name =  getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channelId", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
