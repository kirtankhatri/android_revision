package com.example.revision;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {

    View view;
    TextView airplaneModeStatus;

    public AirplaneModeBroadcastReceiver(View view, TextView airplaneModeStatusTV){
        this.view = view;
        this.airplaneModeStatus = airplaneModeStatusTV;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())){
            boolean isAirplaneModeOn = intent.getBooleanExtra("state",false);
            if(isAirplaneModeOn){
                Toast.makeText(view.getContext(),"Airplane Mode is ON",Toast.LENGTH_SHORT).show();
                airplaneModeStatus.setText("Airplane Mode is ON");
            }
            else{
                Toast.makeText(view.getContext(),"Airplane Mode is OFF",Toast.LENGTH_SHORT).show();
                airplaneModeStatus.setText("Airplane Mode is OFF");
            }
        }
    }
}
