package com.example.revision;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompleteBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            Log.i("info ---","RECEIVED BOOT COMPLETED EVENT");
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Log.i("BOOT COMPLETED ------------------ ","BOOT");
        }
    }
}
