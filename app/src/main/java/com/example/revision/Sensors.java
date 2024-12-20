package com.example.revision;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sensors extends AppCompatActivity implements SensorEventListener {

    TextView xCordsView,yCordsView,zCordsView,screenView;
    Sensor acc;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sensors);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        xCordsView = findViewById(R.id.xCordsView);
        yCordsView = findViewById(R.id.yCordsView);
        zCordsView = findViewById(R.id.zCordsView);
        screenView = findViewById(R.id.screenView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager!=null){
//            acc =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            acc = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,acc,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];

//        xCordsView.setText("X : "+String.valueOf(x));
//        yCordsView.setText("Y : "+String.valueOf(y));
//        zCordsView.setText("Z : "+String.valueOf(z));

        float lightLevel = event.values[0];

        if(lightLevel < 10){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            screenView.setText("Screen : Dark");
        }
        else if(lightLevel < 1000){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            screenView.setText("Screen : Dim");
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            screenView.setText("Screen : Light");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}