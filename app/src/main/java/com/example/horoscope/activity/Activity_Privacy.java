package com.example.horoscope.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.horoscope.R;
import com.example.horoscope.ultil.AdmodManager;
import com.example.horoscope.ultil.ChangeColorStatusBar;
import com.google.android.gms.ads.AdView;

public class Activity_Privacy extends AppCompatActivity {

    private AdView adView;
    private TextView tvTitle, tvContent;
    private int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeColorStatusBar.changeColorStatusBar(this);
        setContentView(R.layout.activity_privacy);

        init();
        getData();
        setUpText();
    }

    private void init(){
        adView = findViewById(R.id.adView);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        AdmodManager.loadBannerAd(adView);
    }

    private void getData(){
        Intent intent = getIntent();
        check = intent.getIntExtra("lala", 0);
    }
    private void setUpText(){
        if(check == 1){
            tvTitle.setText("Privacy Policy");
            tvContent.setText(getString(R.string.policy));
        }else{
            tvTitle.setText("About");
            tvContent.setText(getString(R.string.about));
        }
    }

    public void imgBack(View view) {
        finish();
    }
}
