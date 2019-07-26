package com.example.horoscope.adapter;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.horoscope.fragment.Fragment_Predict;
import com.example.horoscope.fragment.Fragment_Quiz;

public class Viewpager_Adapter extends FragmentPagerAdapter {

    private String[] title;
    private Context context;
    private String zodiacName;
    private int type;

    public Viewpager_Adapter(FragmentManager fm, String zodiacName, String[] title, int type) {
        super(fm);
        this.zodiacName = zodiacName;
        this.title = title;
        this.type = type;
    }

    public Viewpager_Adapter(FragmentManager fm, String[] title, int type) {
        super(fm);
        this.title = title;
        this.context = context;
        this.type = type;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (type) {
            case 1:
                fragment = Fragment_Predict.newInstance(position, zodiacName);
                break;
            case 2:
                fragment = Fragment_Quiz.newInstance(position, title[position]);
                break;
        }
        return fragment;
    }
}
