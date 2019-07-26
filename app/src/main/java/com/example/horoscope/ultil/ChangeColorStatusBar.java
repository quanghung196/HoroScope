package com.example.horoscope.ultil;

import android.app.Activity;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class ChangeColorStatusBar {
    public static void changeColorStatusBar(Activity myActivity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myActivity.getWindow().setStatusBarColor(ContextCompat.getColor(myActivity.getApplicationContext(), color));
        }
    }
}
