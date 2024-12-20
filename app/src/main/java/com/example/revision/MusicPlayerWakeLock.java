package com.example.revision;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MusicPlayerWakeLock extends AppCompatActivity {

    Button playPauseBtn,stopBtn,nextBtn;
    MediaPlayer mediaPlayer;
    int currentIndex = 0;

    PowerManager.WakeLock wakeLock;

    int[] songs ={
            R.raw.song1,
            R.raw.song2,
            R.raw.song3,
            R.raw.song4,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music_player_wake_lock);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        acquireWakeLock();

        playPauseBtn = findViewById(R.id.playPauseBtn);
        stopBtn = findViewById(R.id.stopBtn);
        nextBtn = findViewById(R.id.nextBtn);

        playPauseBtn.setOnClickListener((e)->{
            if(mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(getApplicationContext(),songs[currentIndex]);
            }
            if(mediaPlayer.isPlaying()){
                playPauseBtn.setText("PLAY");
                mediaPlayer.pause();
            }
            else{
                playPauseBtn.setText("PAUSE");
                mediaPlayer.start();
            }
            mediaPlayer.setOnCompletionListener((e2)->{
                releaseWakeLock();
            });
        });

        stopBtn.setOnClickListener((e)->{
            if(mediaPlayer!=null){
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer = null;
                    playPauseBtn.setText("PLAY");
                }
            }

        });

        nextBtn.setOnClickListener((e)->{
            if(mediaPlayer!=null){
                int temp = currentIndex + 1;
                if(temp > songs.length - 1){
                    temp = 0;
                }
                currentIndex = temp;

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }

                mediaPlayer = MediaPlayer.create(getApplicationContext(),songs[currentIndex]);
                playPauseBtn.setText("PAUSE");
                mediaPlayer.start();
            }
        });
    }

    private void acquireWakeLock(){
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        if(powerManager!=null){
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"media_player:wake_lock");
            wakeLock.acquire();
        }
    }

    private void releaseWakeLock(){
        if(wakeLock!=null && wakeLock.isHeld()){
            wakeLock.release();
        }
    }
}