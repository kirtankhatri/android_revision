package com.example.revision;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BroadcastActivitiy extends AppCompatActivity {

    AirplaneModeBroadcastReceiver airplaneModeBroadcastReceiver;
    BatteryStateBroadcastReceiver batteryStateBroadcastReceiver;

    TextView airplaneModeStatusTV,batteryStatusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_broadcast_activitiy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        airplaneModeStatusTV = findViewById(R.id.airplaneModeStatus);
        batteryStatusTV = findViewById(R.id.batteryStatus);

        airplaneModeBroadcastReceiver = new AirplaneModeBroadcastReceiver(findViewById(R.id.main),airplaneModeStatusTV);
        batteryStateBroadcastReceiver = new BatteryStateBroadcastReceiver(findViewById(R.id.main),batteryStatusTV);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter airplaneModeIntentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        IntentFilter batteryStatusIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(airplaneModeBroadcastReceiver,airplaneModeIntentFilter);
        registerReceiver(batteryStateBroadcastReceiver,batteryStatusIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(airplaneModeBroadcastReceiver);
        unregisterReceiver(batteryStateBroadcastReceiver);
    }
}