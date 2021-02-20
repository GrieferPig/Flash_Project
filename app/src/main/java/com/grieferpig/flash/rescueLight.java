package com.grieferpig.flash;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.io.IOException;

public class rescueLight extends AppCompatActivity {

    private Boolean isBlinking = false;
    private CardView frontLayer;
    private Thread blinkThread;
    private SoundPool alarmPool;
    private int streamID;

    private int beforeVol;

    private boolean isPlaying;

    private boolean canPlaySound;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_light);
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = 1;
        window.setAttributes(lp);

        frontLayer = findViewById(R.id.frontLayer);
        frontLayer.setAlpha(0);

        canPlaySound = Boolean.parseBoolean(
                new StorageMan(
                configOptions.emerSoundEnabled.toString(),
                rescueLight.this)
                .read());


    }
    private void on(){
        frontLayer.setAlpha(0);
    }

    private void off(){
        frontLayer.setAlpha(1);
    }

    public void onBlinkClick(View v){
        if(!isBlinking){
            isBlinking = true;
            blinkThread = new Thread(){
                public void run(){
                    try {
                        off();
                        sleep(2000);
                        while (true) {
                            on();
                            sleep(500);
                            off();
                            sleep(500);
                            on();
                            sleep(500);
                            off();
                            sleep(500);
                            on();
                            sleep(500);
                            off();
                            sleep(1000);

                            on();
                            sleep(1200);
                            off();
                            sleep(1000);
                            on();
                            sleep(1200);
                            off();
                            sleep(1000);
                            on();
                            sleep(1200);
                            off();
                            sleep(1000);

                            on();
                            sleep(500);
                            off();
                            sleep(500);
                            on();
                            sleep(500);
                            off();
                            sleep(500);
                            on();
                            sleep(500);
                            off();
                            sleep(2000);
                        }
                    }catch(Exception e){
                        //bye exceptions yeeeeeah
                        e=null;
                    }
                }
            };
            blinkThread.start();
            if(canPlaySound){
                try {
                    startAlarm();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            frontLayer.setAlpha(0);
            blinkThread.interrupt();
            blinkThread = null;
            isBlinking = false;
            if(canPlaySound) {
                stopAlarm();
            }
        }
    }

    public void startAlarm() throws IOException {
        AudioManager a = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        beforeVol = a.getStreamVolume(AudioManager.STREAM_MUSIC);
        a.setStreamVolume(AudioManager.STREAM_MUSIC, 100, AudioManager.FLAG_PLAY_SOUND);
        a = null;
        alarmPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        streamID = alarmPool.load(getApplicationContext().getAssets().openFd("alarm_mix.mp3"), 1);
        alarmPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool arg0, int arg1, int arg2) {
                alarmPool.play(streamID, 100, 100, 1, -1, 1.0f);
                isPlaying = true;
            }
        });
    }

    public void stopAlarm(){
        alarmPool.stop(streamID);
        AudioManager a = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        a.setStreamVolume(AudioManager.STREAM_MUSIC, beforeVol, AudioManager.FLAG_PLAY_SOUND);
        a = null;
        beforeVol = 0;
        isPlaying = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isPlaying) {
            stopAlarm();
        }
        alarmPool = null;
    }

}