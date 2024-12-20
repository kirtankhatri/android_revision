package com.example.revision;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WebViewDemo extends AppCompatActivity {

    VideoView videoView;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_view_demo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoView = findViewById(R.id.videoView);
        webView = findViewById(R.id.webView);

        videoView.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);

//        loadVideoFromUrl();
        loadWebView();
    }

    private void loadWebView(){

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String iframe = "<iframe width=\"100%\" height=\"40%\" src=\"https://www.youtube.com/embed/m25ppbdW5Kc?si=lEpP8E-a3pyXuuKW\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView.loadData(iframe,"text/html","utf-8");
        webView.setWebChromeClient(new MyCustomView());
    }

    private void loadVideoFromUrl(){
        try{
            Uri uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
            MediaController mediaController = new MediaController(this);
//            mediaController.setAnchorView(videoView);
            videoView.setVideoURI(uri);
            videoView.setMediaController(mediaController);
            videoView.setOnPreparedListener((e)->{
                videoView.start();
            });
        }
        catch (Exception e){
            Log.d("Error","Invalid url");
        }
    }

    private class MyCustomView extends WebChromeClient{
        View fullscreen = null;

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
            fullscreen.setVisibility(View.GONE);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);

            if(fullscreen!=null){
                ((FrameLayout)getWindow().getDecorView()).removeView(fullscreen);
            }

            fullscreen = view;

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );

            fullscreen.setOnApplyWindowInsetsListener((v, insets) -> {
                WindowInsetsCompat windowInsets = WindowInsetsCompat.toWindowInsetsCompat(insets);

                Insets systemBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());

                params.setMargins(
                        systemBarInsets.left,
                        systemBarInsets.top,
                        systemBarInsets.right,
                        systemBarInsets.bottom
                );

                fullscreen.setLayoutParams(params);

                return insets;
            });

            ((FrameLayout)getWindow().getDecorView()).addView(fullscreen);
            fullscreen.setVisibility(View.VISIBLE);
            fullscreen.requestApplyInsets();
        }
    }
}