package com.grieferpig.flash;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private TextView versionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch sw = findViewById(R.id.emergency);
        sw.setChecked(Boolean.parseBoolean(
                new StorageMan(
                        configOptions.emerSoundEnabled.toString()
                        ,this).read()
        ));
        Log.d("IFUCKYOURMOTHER", configOptions.values()+"");
        versionText = findViewById(R.id.versionText);
        versionText.setText("这是第"+BuildConfig.VERSION_CODE+"个发布的版本，版本号为"+BuildConfig.VERSION_NAME);
    }

    public void onClick(View v){
        Switch sw = (Switch)v;
        new StorageMan(configOptions.emerSoundEnabled.toString(),this).write(sw.isChecked()+"");
    }
}