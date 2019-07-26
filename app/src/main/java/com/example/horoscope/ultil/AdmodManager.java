package com.example.horoscope.ultil;

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AdmodManager {

    public static final String idInterstitialAd = "ca-app-pub-3940256099942544/1033173712";
    public static final String idBannerAd = "ca-app-pub-3940256099942544/6300978111";
    public static final String appId = "ca-app-pub-3940256099942544~3347511713";
    private static AdmodManager instance;
    private static final int status = 3;
    private static int count = 1;
    private static InterstitialAd mInterstitialAd;
    private static Context mContext;

    public static AdmodManager getInstance(Context context) {
        if (instance == null) {
            instance = new AdmodManager();
            mContext = context;
        }
        return instance;
    }

    public void initInterstitial() {
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(idInterstitialAd);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d("ddddd", "onAdLoaded");

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.d("ddddd", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.d("ddddd", "onAdOpened");

            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.d("ddddd", "onAdClicked");
                initInterstitial();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.d("ddddd", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                Log.d("ddddd", "onAdClosed");
                initInterstitial();
            }
        });
    }
    /*
    *
    * */
    public static void logEvent() {
        count++;
        if (count > status) {
            if (mInterstitialAd.isLoaded()) {
                count = 1;
                mInterstitialAd.show();
            }
        }
    }

    public static void forceEventShowAds() {
        count = status;
        logEvent();
    }


    // load banner ads khi bằng cách thêm vào file xlm đối tượng adview
    public static void loadBannerAd(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    // load banner ads khi bằng cách thêm vào file xlm đối tượng frame. ===> gán adview vào frame
    public static void loadBannerAd(final FrameLayout frameLayout) {
        final AdView adView = new AdView(mContext);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(idBannerAd);
        final AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                frameLayout.addView(adView);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.d("error_ads", i + "");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdClicked() {
            }
        });
    }

}

