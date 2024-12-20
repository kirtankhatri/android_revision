package com.example.revision;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button goToConnectivityPageBtn,
            goToBroadcastReceiverPageBtn,
            goToAlarmManagerBtn,goToWakeLockPageBtn,
            goToTextToSpeechPageBtn,goToURLImageDownloadPageBtn,
            goToImageCapturePageBtn,goToVideoPlaybackPageBtn,
            goToSensorsPageBtn,goToAnimationPageBtn,goToGoogleMapPageBtn,
            goToWebViewPageBtn,goToPostsPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        goToConnectivityPageBtn = findViewById(R.id.goToConnectivityBtn);
        goToBroadcastReceiverPageBtn = findViewById(R.id.goToBroadcastReceiverBtn);
        goToAlarmManagerBtn = findViewById(R.id.goToAlarmManagerBtn);
        goToWakeLockPageBtn = findViewById(R.id.goToWakeLockPageBtn);
        goToTextToSpeechPageBtn = findViewById(R.id.goToTextToSpeechPageBtn);
        goToURLImageDownloadPageBtn = findViewById(R.id.goToURLImageDownloadPageBtn);
        goToImageCapturePageBtn = findViewById(R.id.goToImageCapturePageBtn);
        goToVideoPlaybackPageBtn = findViewById(R.id.goToVideoPlaybackPageBtn);
        goToSensorsPageBtn = findViewById(R.id.goToSensorsPageBtn);
        goToAnimationPageBtn = findViewById(R.id.goToAnimationPageBtn);
        goToGoogleMapPageBtn = findViewById(R.id.goToGoogleMapPageBtn);
        goToWebViewPageBtn = findViewById(R.id.goToWebViewPageBtn);
        goToPostsPageBtn = findViewById(R.id.goToPostsPage);

        goToConnectivityPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, CheckInternetActivtiy.class);
            startActivity(i);
        });

        goToBroadcastReceiverPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this,BroadcastActivitiy.class);
            startActivity(i);
        });

        goToAlarmManagerBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, AlarmManagerDemo.class);
            startActivity(i);
        });

        goToWakeLockPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, MusicPlayerWakeLock.class);
            startActivity(i);
        });

        goToTextToSpeechPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, TextToSpeechDemo.class);
            startActivity(i);
        });

        goToURLImageDownloadPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, URLImageDownload.class);
            startActivity(i);
        });

        goToImageCapturePageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, ImageCapture.class);
            startActivity(i);
        });

        goToVideoPlaybackPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, VideoPlayback.class);
            startActivity(i);
        });

        goToSensorsPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, Sensors.class);
            startActivity(i);
        });

        goToAnimationPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, Animation.class);
            startActivity(i);
        });

        goToGoogleMapPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, GoogleMapsDemo.class);
            startActivity(i);
        });

        goToWebViewPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, WebViewDemo.class);
            startActivity(i);
        });

        goToPostsPageBtn.setOnClickListener((e)->{
            Intent i = new Intent(MainActivity.this, posts.class);
            startActivity(i);
        });

        getFirebaseToken();
    }
    private void getFirebaseToken(){

       FirebaseMessaging.getInstance().getToken().addOnCompleteListener((task)->{
           if(task.isSuccessful()){
            Log.d("Firebase Token",task.getResult());
           }
           else{
            Log.d("Firebase Token Failure","Failed to get firebase token");
           }
       });
    }
}