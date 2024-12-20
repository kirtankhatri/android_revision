package com.example.revision;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryStateBroadcastReceiver extends BroadcastReceiver {

    View view;
    TextView batteryStatus;

    public BatteryStateBroadcastReceiver(View view,TextView batteryStatusTV){
        this.view = view;
        this.batteryStatus = batteryStatusTV;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);

        batteryStatus.setText(level +" %");

        if(status ==  BatteryManager.BATTERY_STATUS_CHARGING){
            Toast.makeText(view.getContext(), "Batter Charging", Toast.LENGTH_SHORT).show();
        }

        if(status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
            Toast.makeText(view.getContext(), "Batter not Charging", Toast.LENGTH_SHORT).show();
        }

    }
}
