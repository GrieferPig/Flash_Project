package com.grieferpig.flash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Light whiteLight = new Light(this,
                whiteLightActivity.class,
                "WHITE",
                "ILLUMINATION",
                R.mipmap.whitelight);
        Light rescueLight = new Light(this,
                rescueLight.class,
                "RESCUE",
                "FOR EMERGENCY",
                R.mipmap.rescuelight);
        List<Light> itemList = new ArrayList<Light>();
        itemList.add(whiteLight);
        itemList.add(rescueLight);
        itemView = findViewById(R.id.itemView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        itemView.setLayoutManager(layoutManager);
        ListAdapter a = new ListAdapter(itemList);
        itemView.setAdapter(a);
        a.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(MainActivity.this, itemList.get(position).lightActivity));
            }
        });

    }

    public void onSettingsClicked(View v){
        startActivity(new Intent(this, SettingsActivity.class));
    }

}