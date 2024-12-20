package com.example.revision;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class URLImageDownload extends AppCompatActivity {

    ImageView urlImageView;
    EditText imageUrlInput;
    Button downloadBtn,setImageBtn;

    ExecutorService executorService;
    Handler mainLoopHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_urlimage_download);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        executorService = Executors.newSingleThreadExecutor();
        mainLoopHandler = new Handler(Looper.getMainLooper());

        downloadBtn = findViewById(R.id.downloadBtn);
        setImageBtn = findViewById(R.id.setImageBtn);
        imageUrlInput = findViewById(R.id.urlInput);
        urlImageView = findViewById(R.id.imageFromUrlView);

        setImageBtn.setOnClickListener((e)->{
            executorService.submit(new SetImageInImageViewTask(imageUrlInput.getText().toString()));
        });
    }

//    public class DownloadImageFromUrlTask implements Runnable{
//
//        @Override
//        public void run() {
//
//        }
//    }

    public class SetImageInImageViewTask implements Runnable{

        String imageUrl;

        public SetImageInImageViewTask(String imageUrl){
            this.imageUrl = imageUrl;
        }

        public void loadImageInView(){
            HttpURLConnection httpURLConnection;
            InputStream inputStream;

            try{
                URL url = new URL(imageUrl);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                inputStream = httpURLConnection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                boolean isImageDownloaded = downloadImage();

                mainLoopHandler.post(()->{
                    urlImageView.setImageBitmap(bitmap);
                    if(isImageDownloaded){
                        Toast.makeText(URLImageDownload.this, "Image Downloaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(URLImageDownload.this, "Failed to download image", Toast.LENGTH_SHORT).show();
                    }
                });

                Toast.makeText(URLImageDownload.this, "Image loaded successfully", Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Log.i("error ---- ",e.toString());
                Toast.makeText(URLImageDownload.this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }

        private boolean downloadImage(){
            try{
                Uri uri = Uri.parse(imageUrl);

                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request downloadRequest = new DownloadManager.Request(uri);

                Calendar calendar = Calendar.getInstance();
                Date d = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                String imageName = sdf.format(d);

                downloadRequest.setTitle("Image");
                downloadRequest.setDescription("This image is downloaded from network");
                downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/downloads/"+imageName);

                downloadManager.enqueue(downloadRequest);
                return true;
            }
            catch(Exception e){
                Log.i("error ----",e.toString());
                return false;
            }
        }

        @Override
        public void run() {
            loadImageInView();
        }
    }
}