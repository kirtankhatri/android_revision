package com.example.revision;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheckInternetActivtiy extends AppCompatActivity {

    Button checkInternetActivityBtn;
    TextView connectivityStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_internet_activtiy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        checkInternetActivityBtn = findViewById(R.id.checkConnectivityBtn);
        connectivityStatusText = findViewById(R.id.connectivityText);
        connectivityStatusText.setText("");

        checkInternetActivityBtn.setOnClickListener((e)->{
            boolean isInternetConnected = checkConnectivity();
            if(isInternetConnected){
                connectivityStatusText.setText("INTERNET CONNECTED");
            }
            else{
                connectivityStatusText.setText("NO INTERNET CONNECTION");
            }
        });

    }

    private boolean checkConnectivity(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        
        if(networkInfo!=null){
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this, "Wifi connected successfully", Toast.LENGTH_SHORT).show();
                return true;
            }
            if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this, "Mobile internet connected successfully", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        return false;
    }
}