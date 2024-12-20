package com.example.revision;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    NotificationHelper notificationHelper;
    @Override
    public void onReceive(Context context, Intent intent) {
        notificationHelper = new NotificationHelper(context);
        notificationHelper.showNotification(1001,"Alarm Title","Alarm Text");
    }
}
