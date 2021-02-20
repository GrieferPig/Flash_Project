package com.grieferpig.flash;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class whiteLightActivity extends AppCompatActivity {

    SeekBar brightnessSlider;
    int brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_light);
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = 1;
        window.setAttributes(lp);
        brightnessSlider = findViewById(R.id.brightnessSlider);
        brightnessSlider.setProgress(100);
        brightnessSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brightness = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Snackbar.make(seekBar,"设置亮度为"+brightness, Snackbar.LENGTH_SHORT).show();
                WindowManager.LayoutParams lp = window.getAttributes();
                DecimalFormat df = new DecimalFormat("0.00");
                double tmpVal = Double.parseDouble(df.format((double) brightness/100));
                lp.screenBrightness = (float)tmpVal;
                window.setAttributes(lp);
            }
        });
    }
}