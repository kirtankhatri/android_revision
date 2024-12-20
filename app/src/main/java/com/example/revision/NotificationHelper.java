package com.example.revision;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class NotificationHelper {

    public static String CHANNEL_ID="alarm78975";
    public static String CHANNEL_NAME="alarm_notification_channel";
    NotificationManager notificationManager;
    Context context;

    public NotificationHelper(Context context){
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH);
            if(notificationManager!=null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void checkPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity)context,new String[]{Manifest.permission.POST_NOTIFICATIONS},200);
            }
        }
    }

    private Notification buildNotification(String title,String text){
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,CHANNEL_ID);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(text);
        notificationBuilder.setSmallIcon(R.drawable.baseline_alarm_24);
        notificationBuilder.setAutoCancel(false);

        return notificationBuilder.build();
    }

    public void showNotification(int notificationId,String title,String text){
        createNotificationChannel();
        checkPermission();
        Notification notification = buildNotification(title,text);
        notificationManager.notify(notificationId,notification);
    }
}
