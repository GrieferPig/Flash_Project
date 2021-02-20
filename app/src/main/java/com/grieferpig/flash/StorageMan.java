package com.grieferpig.flash;

import android.app.Activity;
import android.content.SharedPreferences;

public class StorageMan {

    String storageKey;
    Activity a;
    SharedPreferences writer;

    public StorageMan(String storageKey, Activity a){
        this.storageKey = storageKey;
        this.a = a;
        this.writer = a.getSharedPreferences(storageKey, 0);
    }

    public void write(String context){
        this.writer.edit().putString(this.storageKey, context).apply();
    }

    public String read(){
        return this.writer.getString(this.storageKey, "Na");
    }

    public String getTranslation(int resource_name) {
        return this.a.getResources().getString(resource_name);
    }
}
