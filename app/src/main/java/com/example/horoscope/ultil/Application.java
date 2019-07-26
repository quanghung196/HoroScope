package com.example.horoscope.ultil;

import com.google.android.gms.ads.MobileAds;

import static com.example.horoscope.ultil.AdmodManager.appId;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, appId);
        // khởi tạo đối tượng AdmodManager
        AdmodManager.getInstance(this).initInterstitial();
    }

}
