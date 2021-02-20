package com.grieferpig.flash;

import android.app.Activity;
import android.media.Image;

import androidx.annotation.IdRes;

public class Light {

    Activity opener;
    Class lightActivity;
    String title, desc;
    int imgsrc;

    public Light(Activity opener, Class lightActivity, String title, String desc, int imgsrc){
        this.opener = opener;
        this.lightActivity = lightActivity;
        this.title = title;
        this.desc = desc;
        this.imgsrc = imgsrc;
    }

    public Activity getOpener() {
        return opener;
    }

    public Class getLightActivity() {
        return lightActivity;
    }

    public String getDesc() {
        return desc;
    }

    public int getImgsrc() {
        return imgsrc;
    }

    public String getTitle() {
        return title;
    }
}
