package com.example.revision;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoPlayback extends AppCompatActivity {

    VideoView videoView;
    Button playPauseBtn,nextBtn;
    int currentIndex = 0;

    int[] videos = {
            R.raw.video1,
            R.raw.video2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_playback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoView = findViewById(R.id.videoView);
        playPauseBtn = findViewById(R.id.playPauseBtn);
        nextBtn = findViewById(R.id.nextBtn);

        prepareVideoView();

        playPauseBtn.setOnClickListener((e)->{
            if(videoView.isPlaying()){
                playPauseBtn.setText("Play");
                videoView.pause();
            }
            else{
                playPauseBtn.setText("Pause");
                videoView.start();
            }
        });

        nextBtn.setOnClickListener((e)->{
            int temp = currentIndex + 1;
            if(temp > videos.length - 1){
                temp = 0;
            }
            currentIndex = temp;
            prepareVideoView();
        });

    }

    private void prepareVideoView(){
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+videos[currentIndex]);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener((e)->{
            playPauseBtn.setText("Pause");
            videoView.start();
        });

        videoView.setOnCompletionListener((e)->{
            playPauseBtn.setText("Play");
        });
    }

}