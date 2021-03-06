package com.example.suttipongk.testaboc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/**
 * Created by TOPPEE on 7/19/2017.
 */

public class MainActivityAboc extends AppCompatActivity implements TextToSpeech.OnInitListener {
    MediaPlayer ourSong;

    //Initial Text to Speech
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this,FacePredictionService.class));

        //Initial Text to Speech
        tts = new TextToSpeech(this, this, "com.google.android.tts");

        ourSong = MediaPlayer.create(MainActivityAboc.this, R.raw.intentsuccess);               //Sound
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("checkbox",true);
        if(music == true)
            ourSong.start();

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openStartingPoint = new Intent(getApplicationContext(),IntroSliderAboc.class);
                    startActivity(openStartingPoint);
                }
            }
        };
        timer.start();
    }

    //Initial Text to Speech
    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(new Locale("th"));
            tts.speak("สวัสดี เราอาร์บ๊อก", TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
