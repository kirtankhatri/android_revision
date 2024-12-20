package com.example.revision;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class TextToSpeechDemo extends AppCompatActivity implements TextToSpeech.OnInitListener {
    EditText speechInput;
    Button speakBtn;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_text_to_speech_demo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tts = new TextToSpeech(this,this);

        speechInput = findViewById(R.id.speechInput);
        speakBtn = findViewById(R.id.speakBtn);

        speakBtn.setOnClickListener((e)->{
            String text = speechInput.getText().toString();
            if(!text.isEmpty() || !text.isBlank()){
                speak(text);
            }
        });

    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.ENGLISH);
            if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                Log.i("TTS","Language is not supported");
            }
            else{
                Log.i("TTS","Ready ........");
            }
        }
        else{
            Log.i("TTS","Failed to initialize TTS");
        }
    }

    private void speak(String text){
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
    }
}