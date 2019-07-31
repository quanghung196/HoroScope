package com.example.horoscope.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.horoscope.R;
import com.example.horoscope.adapter.Viewpager_Adapter;
import com.example.horoscope.ultil.ChangeColorStatusBar;
import com.example.horoscope.ultil.GetCurrentYear;

public class Activity_Articel extends AppCompatActivity {

    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    private Viewpager_Adapter adapter;
    private String[] titleCategory = {"Love", "For Girls", "Dating", "Career", "Personality","Friendship"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeColorStatusBar.changeColorStatusBar(this);
        setContentView(R.layout.activity_articel);

        init();
        setUpPageSliding();
    }

    private void init(){
        pagerSlidingTabStrip = findViewById(R.id.pagerSlidingTabStrip);
        viewPager = findViewById(R.id.viewPager);
    }

    private void setUpPageSliding() {
        adapter = new Viewpager_Adapter(getSupportFragmentManager(), titleCategory, 2);
        viewPager.setOffscreenPageLimit(titleCategory.length);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);
    }

    public void imgBack(View view) {
        finish();
    }
}
