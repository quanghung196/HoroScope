package com.example.horoscope.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.example.horoscope.R;
import com.example.horoscope.activity.Activity_SelectZodiac;
import com.example.horoscope.adapter.Viewpager_Adapter;
import com.example.horoscope.ultil.GetCurrentYear;
import com.example.horoscope.ultil.SharePrefManager;

public class Fragment_HoroScope extends Fragment {

    private ImageView imgZodiacPicker;
    private TextView tvZodiacName;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    private Viewpager_Adapter adapter;
    private String TAG = "HoroScopeFragment";
    private String zodiac;
    private Context context;
    private String[] titlePredict = {"Tomorrow", "Today", "Week", "Month", GetCurrentYear.getCurrentYear()};

    public Fragment_HoroScope() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horo_scope, container, false);
        init(view);
        tvZodiacName.setText(zodiac);
        changeImageViewByZodiac();
        setColorTextView();
        setUpPageSliding();
        return view;
    }

    private void setColorTextView() {
        TextPaint paint = tvZodiacName.getPaint();
        float width = paint.measureText(zodiac);

        Shader textShader = new LinearGradient(0, 0, width, tvZodiacName.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        tvZodiacName.getPaint().setShader(textShader);
    }

    private void changeImageViewByZodiac() {
        if (zodiac.contains("Aquarius")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_aquarius);
        } else if (zodiac.contains("Pisces")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_pisces);
        } else if (zodiac.contains("Aries")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_aries);
        } else if (zodiac.contains("Taurus")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_taurus);
        } else if (zodiac.contains("Gemini")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_gemini);
        } else if (zodiac.contains("Cancer")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_cancer);
        } else if (zodiac.contains("Leo")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_leo);
        } else if (zodiac.contains("Virgo")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_virgo);
        } else if (zodiac.contains("Libra")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_libra);
        } else if (zodiac.contains("Scorpio")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_scorpio);
        } else if (zodiac.contains("Sagittarius")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_sagittarius);
        } else if (zodiac.contains("Capricorn")) {
            imgZodiacPicker.setBackgroundResource(R.drawable.ic_capricom);
        }
    }

    private void init(View view) {
        zodiac = SharePrefManager.getZodiac(getActivity());
        imgZodiacPicker = view.findViewById(R.id.imgZodiacPicker);
        tvZodiacName = view.findViewById(R.id.tvZodiacName);
        pagerSlidingTabStrip = view.findViewById(R.id.pagerSlidingTabStrip);
        viewPager = view.findViewById(R.id.viewPager);

        imgZodiacPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Activity_SelectZodiac.class);
                startActivity(intent);
            }
        });
    }

    private void setUpPageSliding() {
        adapter = new Viewpager_Adapter(getChildFragmentManager(), zodiac, titlePredict, 1);
        viewPager.setOffscreenPageLimit(titlePredict.length);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);
        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //AdmodManager.logEvent();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
